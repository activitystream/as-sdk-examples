package com.activitystream.sdk.examples.vertical.ticketing;

import au.com.bytecode.opencsv.CSVReader;
import com.activitystream.core.model.relations.ASEventRelationTypes;
import com.activitystream.core.model.stream.ImportanceLevel;
import com.activitystream.sdk.ASConstants;
import com.activitystream.sdk.ASEvent;
import com.activitystream.sdk.utilities.SimpleArrayWrapper;
import net.javacrumbs.jsonunit.JsonAssert;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TicketingExamplesIOTest {

    private static final String BASE_PATH = "src/test/resources/test-data/ticketing/";

    enum SampleCSVFields {
        event_id, event_name, ticket_id, customer_id, scanned_at, is_scanned, gate_id, venue_id, venue_section_name, section_row, seat_number, seat_label, season_name, season_id;
    }

    @Test
    public void createTicketUsedEvent() throws IOException {
        ASEvent ticketUsed = null;

        CSVReader csvReader = new CSVReader(new FileReader(BASE_PATH + "input-samples/input-sample.json"), ',');
        String[] header = csvReader.readNext(); // do nothing with the header at the moment
        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            SimpleArrayWrapper reader = new SimpleArrayWrapper(nextLine);

            //Every ASEvent should have eventType, origin, occurredAt and ACTOR relation
            //Every ASEntity should have entityType and id
            ticketUsed = new ASEvent(ASEvent.PRE.AS_EVENT_TICKET_SCAN_ENTERED, "SpaceJam.scanning")
                    .withOccurredAt(reader.column(SampleCSVFields.scanned_at))
                    .withImportance(ImportanceLevel.IMPORTANT)
                    .withRelationIfValid(ASEventRelationTypes.ACTOR, "Customer", reader.column(SampleCSVFields.customer_id))
                    .withRelationIfValid(ASEventRelationTypes.INVOLVES, "Venue", reader.column(SampleCSVFields.venue_id))
                    .withRelationIfValid(ASEventRelationTypes.INVOLVES, "Gate", reader.column(SampleCSVFields.gate_id))
                    .withRelationIfValid(ASEventRelationTypes.INVOLVES, "Ticket", reader.column(SampleCSVFields.ticket_id))
                    .withRelationIfValid(ASEventRelationTypes.AFFECTS + ":" + ASConstants.REL_GRANTS_ACCES_TO, "Seat", reader.column(SampleCSVFields.venue_section_name) + "_" + reader.column(SampleCSVFields.section_row) + "_" + reader.column(SampleCSVFields.seat_number));

            ticketUsed.withProperties("scanned", reader.column(SampleCSVFields.is_scanned));
        }
        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "output-samples/ticketUsedIO-sample.json"), "UTF-8"), ticketUsed.toJSON());
    }
}
