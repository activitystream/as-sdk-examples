package com.activitystream.sdk.examples.vertical.ticketing;

import com.activitystream.sdk.ASEntity;
import com.activitystream.sdk.ASEvent;
import com.activitystream.sdk.ASService;
import net.javacrumbs.jsonunit.JsonAssert;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.TimeZone;


public class TicketingExamplesTest {

    private static final String BASE_PATH = "src/test/resources/test-data/ticketing/";

    @BeforeMethod
    public void setUp() {
        //Sets predefined parameters such as country code, currency and time zone which applies to all messages. It is executed before test cases.
        ASService.setDefaults("US", "USD", TimeZone.getTimeZone("GMT+0:00"));
    }

    @Test
    public void testCreateCustomerEntity() throws Exception {
        ASEntity customerEntity = TicketingExamples.createCustomerEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "customer-sample.json"), "UTF-8"), customerEntity.toJSON());
    }

    @Test
    public void testCreateSeatEntity() throws Exception {
        ASEntity seatEntity = TicketingExamples.createSeatEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "seat-sample.json"), "UTF-8"), seatEntity.toJSON());
    }

    @Test
    public void testCreateVenueEntity() throws Exception {
        ASEntity venueEntity = TicketingExamples.createVenueEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "venue-sample.json"), "UTF-8"), venueEntity.toJSON());
    }

    @Test
    public void testCreateEventEntity() throws Exception {
        ASEntity eventEntity = TicketingExamples.createEventEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "event-sample.json"), "UTF-8"), eventEntity.toJSON());
    }

    @Test
    public void testCreateOrderEntity() throws Exception {
        ASEntity orderEntity = TicketingExamples.createOrderEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "order-sample.json"), "UTF-8"), orderEntity.toJSON());
    }

    @Test
    public void testCreateOfferEntity() throws Exception {
        ASEntity offerEntity = TicketingExamples.createOfferEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "offer-sample.json"), "UTF-8"), offerEntity.toJSON());
    }

    @Test
    public void testCreateTicketEntity() throws Exception {
        ASEntity ticketEntity = TicketingExamples.createTicketEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "ticket-sample.json"), "UTF-8"), ticketEntity.toJSON());
    }

    @Test
    public void testCreateTransactionCompletedEvent() throws Exception {
        ASEvent transactionCompletedEvent = TicketingExamples.createTransactionCompletedEvent();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "transactionCompleted-sample.json"), "UTF-8"), transactionCompletedEvent.toJSON());
    }

    @Test
    public void testCreateSeatAssignedEvent() throws Exception {
        ASEvent seatAssignedEvent = TicketingExamples.createSeatAssignedEvent();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "seatAssigned-sample.json"), "UTF-8"), seatAssignedEvent.toJSON());
    }

    @Test
    public void testCreateSeatUnassignedEvent() throws Exception {
        ASEvent seatUnassignedEvent = TicketingExamples.createSeatUnassignedEvent();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "seatUnassigned-sample.json"), "UTF-8"), seatUnassignedEvent.toJSON());
    }

    @Test
    public void testCreateTicketUsedEvent() throws Exception {
        ASEvent ticketUsedEvent = TicketingExamples.createTicketUsedEvent();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "ticketUsed-sample.json"), "UTF-8"), ticketUsedEvent.toJSON());
    }
}