package com.activitystream.sdk.examples.vertical.ticketing;

import au.com.bytecode.opencsv.CSVReader;
import com.activitystream.core.model.relations.ASEventRelationTypes;
import com.activitystream.core.model.relations.Relation;
import com.activitystream.core.model.stream.ImportanceLevel;
import com.activitystream.sdk.ASConstants;
import com.activitystream.sdk.ASEntity;
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
        event_id, event_name, ticket_id, customer_id, customer_name, scanned_at, is_scanned, gate_id, venue_id, venue_section_name, section_row, seat_number, seat_label, season_name, season_id;
    }

    @Test
    public void createTicketUsedEvent() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(BASE_PATH + "input-samples/input-sample.json"), ',');
        String[] header = csvReader.readNext(); // do nothing with the header at the moment
        String[] nextLine;

        StringBuffer generatedMessages = new StringBuffer();
        while ((nextLine = csvReader.readNext()) != null) {
            SimpleArrayWrapper reader = new SimpleArrayWrapper(nextLine);

            //Every ASEntity should have at least entityType and id
            ASEntity customer = new ASEntity("Customer", reader.column(SampleCSVFields.customer_id), reader.column(SampleCSVFields.customer_name));

            //Every ASEvent should have at least eventType, origin, occurredAt and ACTOR relation
            ASEvent ticketUsed = new ASEvent(ASEvent.PRE.AS_EVENT_TICKET_SCAN_ENTERED, "SpaceJam.scanning")
                    .withOccurredAt(reader.column(SampleCSVFields.scanned_at))
                    .withImportance(ImportanceLevel.IMPORTANT)
                    //ASEntity objects could be created separately like "Customer" or inline like "Venue", "Gate", "Ticket" or "Seat"
                    .withRelation(new Relation(ASEventRelationTypes.ACTOR, customer))
                    .withRelationIfValid(ASEventRelationTypes.INVOLVES, "Venue", reader.column(SampleCSVFields.venue_id))
                    .withRelationIfValid(ASEventRelationTypes.INVOLVES, "Gate", reader.column(SampleCSVFields.gate_id))
                    .withRelationIfValid(ASEventRelationTypes.INVOLVES, "Ticket", reader.column(SampleCSVFields.ticket_id))
                    .withRelationIfValid(ASEventRelationTypes.AFFECTS + ":" + ASConstants.REL_GRANTS_ACCES_TO, "Seat", reader.column(SampleCSVFields.venue_section_name) + "_" + reader.column(SampleCSVFields.section_row) + "_" + reader.column(SampleCSVFields.seat_number));

            ticketUsed.withProperties("scanned", reader.column(SampleCSVFields.is_scanned));

            String generatedASMessage = ticketUsed.toJSON();

            //In reality you would like to output generated AS message to ActivityStream message queue, but it is being appended here for test purposes
            generatedMessages.append(generatedASMessage).append("\n");
        }
        //Verifying that output looks as expected
        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "output-samples/ticketUsedIO-sample.json"), "UTF-8"), generatedMessages.toString());
    }
}
