package com.activitystream.sdk.examples.vertical.ticketing;

import com.activitystream.core.model.aspects.AddressAspect;
import com.activitystream.core.model.aspects.ClassificationAspect;
import com.activitystream.core.model.aspects.ClientIpAspect;
import com.activitystream.core.model.aspects.DemographyAspect;
import com.activitystream.core.model.aspects.GeoLocationAspect;
import com.activitystream.core.model.aspects.InventoryAspect;
import com.activitystream.core.model.aspects.ItemsManager;
import com.activitystream.core.model.aspects.PresentationAspect;
import com.activitystream.core.model.aspects.TimedAspect;
import com.activitystream.core.model.aspects.TrafficSource;
import com.activitystream.core.model.aspects.TrafficSourceAspect;
import com.activitystream.core.model.relations.ASEntityRelationTypes;
import com.activitystream.core.model.relations.ASEventRelationTypes;
import com.activitystream.core.model.relations.Relation;
import com.activitystream.core.model.stream.ImportanceLevel;
import com.activitystream.sdk.ASConstants;
import com.activitystream.sdk.ASEntity;
import com.activitystream.sdk.ASEvent;
import com.activitystream.sdk.ASLineItem;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketingExamples {

    public static ASEntity createCustomerEntity() {
        ASEntity customer = new ASEntity("Customer", "123456");
        ClassificationAspect classificationAspect = new ClassificationAspect()
                .withType("regular")
                .withVariant("subscribed")
                .withTags("VIP")
                .withCategories("loyal customer");
        customer.withAspect(classificationAspect);

        AddressAspect addressAspect = new AddressAspect()
                .withCountry("Barbados")
                .withCountryCode("BB")
                .withCity("Bridgetown")
                .withAddress("St. Michael 22")
                .withAddress2("Broad Street 8")
                .withPostCode("BB25065");
        customer.withAspect(addressAspect);

        PresentationAspect presentationAspect = new PresentationAspect()
                .withLabel("Robert King")
                .withDescription("Mostly buys tickets for matches in the Basketball Champions League.")
                .withThumbnail("CusID123456.pic")
                .withDetailsUrl("http://weblog.infoworld.com/Robert.King/");
        customer.withAspect(presentationAspect);

        DemographyAspect demographyAspect = new DemographyAspect()
                .withBirthDate(DateTime.parse("1968-07-14").toString())
                .withGender("Male")
                .withMosaicGroup("Basketball Lover")
                .withIncome("400k$ - 800k$")
                .withMaritalStatus("Married")
                .withEducation("BA in psychology from the University of Washington.")
                .withEmployment("Governmental Employee")
                .withEthnicity("Caribbean");
        customer.withAspect(demographyAspect);

        customer.withRelationIfValid(ASEntityRelationTypes.AKA, "Email", "robert.king@gmail.com");
        customer.withRelationIfValid(ASEntityRelationTypes.AKA, "Phone", "+122123654789");
        customer.withDimension("created_at", "2017-12-13T12:05:01.369Z");
        //Property indicates whether the customer is subscribed to a mailing list or not
        customer.withProperties("email_permission", true);

        return customer;
    }

    public static ASEntity createSeatEntity() {
        ASEntity seat = new ASEntity("Seat", "4911_23_2_12");

        PresentationAspect seatPresentationAspect = new PresentationAspect();
        seatPresentationAspect.withLabel("Seat VIP Balcony VIP23");
        seat.withAspect(seatPresentationAspect);

        ASEntity venueSection = new ASEntity("VenueSection", "23-vip-balcony");
        PresentationAspect venueSectionPresentationAspect = new PresentationAspect();
        venueSectionPresentationAspect.withLabel("VIP Balcony");
        venueSection.withAspect(venueSectionPresentationAspect);

        ASEntity sectionRow = new ASEntity("SectionRow", "2-vip-row");
        PresentationAspect sectionRowPresentationAspect = new PresentationAspect();
        sectionRowPresentationAspect.withLabel("VIP2");
        sectionRow.withAspect(sectionRowPresentationAspect);

        //Refers to Venue entity that was previously created
        venueSection.withRelationIfValid(ASEntityRelationTypes.LOCATED_IN, "Venue", "7856");

        sectionRow.withRelation(ASEntityRelationTypes.LOCATED_IN, venueSection);

        seat.withRelation(ASEntityRelationTypes.LOCATED_IN, sectionRow);

        return seat;
    }

    public static ASEntity createVenueEntity() {
        ASEntity venue = new ASEntity("Venue", "7856");

        ClassificationAspect classificationAspect = new ClassificationAspect();
        classificationAspect.withType("Stadium");
        venue.withAspect(classificationAspect);

        PresentationAspect presentationAspect = new PresentationAspect()
                .withLabel("The Tune Stadium")
                .withDetailsUrl("http://space-jam.wikia.com/wiki/The_Tune_Stadium")
                .withThumbnail("http://space-jam.wikia.com/wiki/File:Stadium.png")
                .withIcon("The_Tune_Stadium");
        venue.withAspect(presentationAspect);

        AddressAspect addressAspect = new AddressAspect()
                .withAddress("Galaxy Avenue 30")
                .withCity("New York City")
                .withPostCode("10001")
                .withCountry("New York")
                .withCountryCode("NY")
                .withState("United States")
                .withStateCode("US");
        venue.withAspect(addressAspect);

        GeoLocationAspect geoLocationAspect = new GeoLocationAspect()
                .withLatitude(55.6488)
                .withLongitude(10.4176);
        venue.withAspect(geoLocationAspect);

        venue.withMetric("Capacity", 1029000);
        venue.withMetric("built_in", 1996);

        return venue;
    }

    public static ASEntity createEventEntity() {
        ASEntity event = new ASEntity("Event", "852654");

        ClassificationAspect classificationAspect = new ClassificationAspect()
                .withType("Match")
                .withVariant("Basketball match")
                .withCategories("Basketball", "NBA")
                .withTags("Michael Jordan", "Tune Squad ", "The Tune Stadium");
        event.withAspect(classificationAspect);

        PresentationAspect presentationAspect = new PresentationAspect()
                .withLabel("Tune Squad - Monstars")
                .withDescription("The Ultimate Game")
                .withThumbnail("https://www.warnerbros.com/archive/spacejam/movie/jam.htm");
        event.withAspect(presentationAspect);

        TimedAspect timedAspect = new TimedAspect()
                .withPeriod("begins", "2018-07-14T10:00:00.000-04:00")
                .withPeriod("on_sale", "2017-07-14T10:00:00.000-04:00")
                .withPeriod("gate_open", "2018-07-14T10:00:00.000-04:00");
        event.withAspect(timedAspect);

        InventoryAspect inventoryAspect = new InventoryAspect();
        inventoryAspect.put(ASConstants.FIELD_ITEMS_FOR_SALE, "600.0");
        inventoryAspect.put(ASConstants.FIELD_ITEMS_IN_STOCK, "400.0");
        inventoryAspect.put(ASConstants.FIELD_ITEMS_SOLD, "310.0");
        inventoryAspect.put(ASConstants.FIELD_ITEMS_RESERVED, "64.0");
        inventoryAspect.put(ASConstants.FIELD_ITEMS_UNSELLABLE, "2.0");
        inventoryAspect.put(ASConstants.FIELD_ITEMS_COMPLIMENTARY, "6.0");

        List<Map<String, Object>> priceCategories = new ArrayList<>();

        Map<String, Object> priceCategoryMap1 = new HashMap<>();
        priceCategoryMap1.put(ASConstants.FIELD_PRICE_CATEGORY, "General Admission");
        priceCategoryMap1.put(ASConstants.FIELD_ITEMS_FOR_SALE, "400.0");
        priceCategoryMap1.put(ASConstants.FIELD_ITEMS_IN_STOCK, "300.0");
        priceCategoryMap1.put(ASConstants.FIELD_ITEMS_SOLD, "250.0");
        priceCategoryMap1.put(ASConstants.FIELD_ITEMS_RESERVED, "50.0");
        priceCategoryMap1.put(ASConstants.FIELD_ITEMS_UNSELLABLE, "0.0");
        priceCategoryMap1.put(ASConstants.FIELD_ITEMS_COMPLIMENTARY, "0.0");
        priceCategories.add(priceCategoryMap1);

        Map<String, Object> priceCategoryMap2 = new HashMap<>();
        priceCategoryMap2.put(ASConstants.FIELD_PRICE_CATEGORY, "VIP Balcony");
        priceCategoryMap2.put(ASConstants.FIELD_ITEMS_FOR_SALE, "200.0");
        priceCategoryMap2.put(ASConstants.FIELD_ITEMS_IN_STOCK, "100.0");
        priceCategoryMap2.put(ASConstants.FIELD_ITEMS_SOLD, "80.0");
        priceCategoryMap2.put(ASConstants.FIELD_ITEMS_RESERVED, "14.0");
        priceCategoryMap2.put(ASConstants.FIELD_ITEMS_UNSELLABLE, "2.0");
        priceCategoryMap2.put(ASConstants.FIELD_ITEMS_COMPLIMENTARY, "6.0");
        priceCategories.add(priceCategoryMap2);

        inventoryAspect.put(ASConstants.FIELD_PRICE_CATEGORIES, priceCategories);
        event.withAspect(inventoryAspect);
        //Refers to Venue entity that was previously  created
        event.withRelationIfValid(ASEntityRelationTypes.HOSTED_AT, "Venue", "7856");
        event.withRelation(new Relation(ASEntityRelationTypes.SUPPLIED_BY, new ASEntity("Organizer", "WB_75321", "Jam Central")));
        event.withRelationIfValid(ASEntityRelationTypes.PART_OF, "Campaign", "851236");
        event.withRelation(new Relation(ASEntityRelationTypes.PART_OF, new ASEntity("Season", "132", "2017–18 NBA season")));

        return event;
    }

    public static ASEntity createOrderEntity() {
        ASEntity order = new ASEntity("Order", "321");
        ClassificationAspect classificationAspect = new ClassificationAspect();
        classificationAspect.setType("Individual");
        order.withAspect(classificationAspect);
        order.withDimension("first_purchase", "false");

        return order;
    }

    public static ASEntity createOfferEntity() {
        ASEntity offer = new ASEntity("Offer", "852-98874");
        //Refers to Event entity that was previously created
        offer.withRelationIfValid(ASEntityRelationTypes.ASSOCIATED_WITH, "Event", "852654");

        ClassificationAspect classificationAspect = new ClassificationAspect();
        classificationAspect.setType("General Offer");
        offer.withAspect(classificationAspect);

        TimedAspect timedAspect = new TimedAspect();
        timedAspect.withPeriod("offer_valid_date", "2017-12-24T10:00:00.000-04:00");
        offer.withAspect(timedAspect);

        PresentationAspect presentationAspect = new PresentationAspect()
                .withLabel("Holiday Offers")
                .withDescription("Black Friday Discounts");
        offer.withAspect(presentationAspect);

        return offer;
    }

    public static ASEvent createPurchaseCompletedEvent() {
        ASEvent purchaseCompleted = new ASEvent(ASEvent.PRE.AS_COMMERCE_PURCHASE_COMPLETED, "SpaceJam.commerce")
                .withOccurredAt("2017-12-13T10:18:27.480Z")
                .withImportance(ImportanceLevel.IMPORTANT)
                //Refers to entities that were previously created: Customer and Order
                //":BUYER" is an extension of predefined entity relationship type to specify custom role
                .withRelationIfValid(ASEventRelationTypes.ACTOR + ":BUYER", "Customer", "123456")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS, "Order", "321")
                .withDimension("sales_channel", "Internet")
                .withDimensions("sales_channel_type", "Web store")
                .withDimension("first_purchase", "false")
                .withDimensions("order_type", "CC54")
                .withDimensions("payment_method", "Credit card");

        ItemsManager itemsManager = new ItemsManager();

        //Creates line item for each purchased ticket
        ASLineItem lineItem1 = new ASLineItem()
                .withProduct(ASLineItem.LINE_TYPES.PURCHASED, new ASEntity("Event", "852654"))
                .withItemCount(1.0)
                .withItemPrice(10.0)
                .withDescription("European Cup")
                .withCurrency("Euro")
                .withVariant("VIP Balcony Section 2")
                .withPriceType("Offer/852456")
                //Property indicates whether the ticket card is free or not
                .withComplimentary(false)
                .withPriceCategory("VIP")
                .withPaymentMethod("Credit card")
                .withValidFrom(DateTime.parse("2017-12-13T10:18:27.480Z"))
                //Item number provided for each purchased ticket
                .withLineId("1")
                .withDimensions("delivery_method", "E-Ticket")
                .withDimension("subscription", "yes")
                //Refers to entities that were previously created: Offer and Ticket
                .withRelationIfValid(ASEntityRelationTypes.RATED_BY, "Offer", "852456")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS + ":CREATES", "Ticket", "123");
        itemsManager.mergeItemLine(lineItem1);

        //Creates line item for each purchased ticket
        ASLineItem lineItem2 = new ASLineItem()
                .withProduct(ASLineItem.LINE_TYPES.PURCHASED, new ASEntity("Event", "852654"))
                .withItemCount(1.0)
                .withItemPrice(10.0)
                .withDescription("European Cup")
                .withCurrency("Euro")
                .withVariant("VIP Balcony Section 2")
                .withPriceType("Offer/852456")
                //Property indicates whether the ticket card is free or not
                .withComplimentary(false)
                .withPriceCategory("VIP")
                .withPaymentMethod("Credit card")
                .withValidFrom(DateTime.parse("2017-12-13T10:18:27.480Z"))
                //Item number provided for each purchased ticket
                .withLineId("2")
                .withDimensions("delivery_method", "E-Ticket")
                .withDimension("subscription", "yes")
                //Refers to entities that were previously created: Offer and Ticket
                .withRelationIfValid(ASEntityRelationTypes.RATED_BY, "Offer", "852456")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS + ":CREATES", "Ticket", "456");
        itemsManager.mergeItemLine(lineItem2);

        purchaseCompleted.withAspect(itemsManager);

        TrafficSourceAspect trafficSourceAspect = new TrafficSourceAspect();
        TrafficSource trafficSource = new TrafficSource()
                .withType("direct")
                .withSource("Telefon")
                .withMedium("Billetto");
        purchaseCompleted.withAspect(trafficSourceAspect.addTrafficSource(trafficSource));

        ClientIpAspect clientIpAspect = new ClientIpAspect();
        clientIpAspect.withIp("216.3.128.12");
        purchaseCompleted.withAspect(clientIpAspect);

        return purchaseCompleted;
    }

    public static ASEvent createSeatAssignedEvent() {
        ASEvent assignedEvent = new ASEvent()
                .withType(ASEvent.PRE.AS_EVENT_SEAT_ASSIGNED)
                .withOccurredAt("2017-12-13T10:18:27.433Z")
                .withOrigin("SpaceJam")
                .withImportance(ImportanceLevel.IMPORTANT)
                //Refers to entities that were previously created: Venue, Seat, Order, Event
                .withRelationIfValid(ASEventRelationTypes.ACTOR, "Venue", "7856")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS, "Seat", "4911_23_2_12")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS, "Order", "123")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS, "Event", "852654")
                .withAspect(new TimedAspect("show_starts", "2018-12-13T10:18:27.433Z", null))
                .withMetric("price", -520.6, null)
                .withDimensions("section", "23")
                .withDimension("row", "2")
                .withDimension("seat", "12B");

        return assignedEvent;
    }

    public static ASEvent createSeatUnassignedEvent() {
        ASEvent unassignedEvent = new ASEvent()
                .withType(ASEvent.PRE.AS_EVENT_SEAT_UNASSIGNED)
                .withOccurredAt("2017-12-13T10:18:27.496Z")
                .withOrigin("SpaceJam")
                .withImportance(ImportanceLevel.IMPORTANT)
                //Refers to entities that were previously created: Venue, Seat, Order, Event
                .withRelationIfValid(ASEventRelationTypes.ACTOR, "Venue", "7856")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS, "Seat", "4911_23_2_12")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS, "Order", "123")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS, "Event", "852654")
                .withAspect(new TimedAspect("show_starts", "2018-12-13T12:08:56.740Z", null))
                .withMetrics("price", -520.6)
                .withDimensions("section", "23")
                .withDimension("row", "2")
                .withDimension("seat", "12B");

        return unassignedEvent;
    }

    public static ASEvent createTicketUsedEvent() {
        ASEvent ticketUsed = new ASEvent(ASEvent.PRE.AS_EVENT_TICKET_SCAN_ENTERED, "SpaceJam.scanning")
                .withOccurredAt("2017-12-13T10:18:27.496Z")
                .withImportance(ImportanceLevel.IMPORTANT)
                //Refers to entities that were previously created: Customer, Venue, Ticket
                .withRelationIfValid(ASEventRelationTypes.ACTOR, "Customer", "951")
                .withRelationIfValid(ASEventRelationTypes.INVOLVES, "Venue", "7856")
                .withRelationIfValid(ASEventRelationTypes.INVOLVES, "Gate", "055")
                //.withRelationIfValid(ASEventRelationTypes.INVOLVES, "Barcode", "0000097788")
                .withRelationIfValid(ASEventRelationTypes.INVOLVES, "Ticket", "852456")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS + ":" + ASConstants.REL_GRANTS_ACCES_TO, "Seat", "4911_23_2_12");

        ticketUsed.withProperties("scanned", "true");

        return ticketUsed;
    }
}


