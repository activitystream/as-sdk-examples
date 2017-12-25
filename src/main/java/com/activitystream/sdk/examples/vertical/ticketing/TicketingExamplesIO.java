package com.activitystream.sdk.examples.vertical.ticketing;

import au.com.bytecode.opencsv.CSVReader;
import com.activitystream.core.model.relations.ASEventRelationTypes;
import com.activitystream.core.model.stream.ImportanceLevel;
import com.activitystream.sdk.ASConstants;
import com.activitystream.sdk.ASEvent;
import com.activitystream.sdk.utilities.SimpleArrayWrapper;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketingExamplesIO {

    private static final String BASE_PATH = "src/test/resources/test-data/ticketing/";
    private static List<ASEvent> eventList = new ArrayList<>();

    enum SampleCSVFields {
        event_id, event_name, ticket_id, customer_id, scanned_at, is_scanned, gate_id, venue_id, venue_section_name, section_row, seat_number, seat_label, season_name, season_id
    }

    public static ASEvent[] createTicketUsedEvent() {
        ASEvent ticketUsed = null;

        try (CSVReader csvReader = new CSVReader(new FileReader(BASE_PATH + "input-sample.json"), ',')) {
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
                        .withRelationIfValid(ASEventRelationTypes.AFFECTS + ":" + ASConstants.REL_GRANTS_ACCES_TO, "Seat", "4911_" + reader.column(SampleCSVFields.venue_section_name) + "_" + reader.column(SampleCSVFields.section_row) + "_" + reader.column(SampleCSVFields.seat_number));

                ticketUsed.withProperties("scanned", reader.column(SampleCSVFields.is_scanned));

                eventList.add(ticketUsed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Array is used for cases with multiple CSV records
        return eventList.toArray(new ASEvent[0]);
    }
}
