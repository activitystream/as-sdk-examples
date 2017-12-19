package com.activitystream.sdk.examples.vertical.mailing;

import com.activitystream.core.model.relations.ASEntityRelationTypes;
import com.activitystream.core.model.relations.ASEventRelationTypes;
import com.activitystream.sdk.ASEntity;
import com.activitystream.sdk.ASEvent;
import com.fasterxml.jackson.core.JsonProcessingException;

import static com.activitystream.core.model.aspects.ClassificationAspect.classification;
import static com.activitystream.core.model.aspects.ClientIpAspect.clientIP;
import static com.activitystream.core.model.aspects.ContentAspect.content;
import static com.activitystream.core.model.aspects.PresentationAspect.presentation;
import static com.activitystream.core.model.aspects.ResolvableAspect.resolvable;
import static com.activitystream.core.model.aspects.TimedAspect.timed;
import static com.activitystream.core.model.aspects.TrafficSource.trafficSource;
import static com.activitystream.core.model.aspects.TrafficSourceAspect.trafficSources;

public class MailingExamples {

    public static ASEvent createEmailSentEvent() throws JsonProcessingException {
        ASEvent emailSentEvent = new ASEvent()
                .withType(ASEvent.PRE.AS_CRM_MESSAGE_EMAIL_SENT)
                .withOccurredAt("2017-02-05T21:37:09.000Z")
                .withOrigin("mail.system")

                .withRelationIfValid(ASEventRelationTypes.ACTOR, "MailCampaign", "852456")
                .withRelationIfValid(ASEventRelationTypes.INVOLVES, "MailSubscriber", "1159")
                .withRelationIfValid(ASEventRelationTypes.INVOLVES, "Email", "pera.detlic@gmail.com")

                .withAspect(resolvable().withExternalId("45834911"))
                .withAspect(clientIP("127.0.0.1"));

        return emailSentEvent;
    }

    public static ASEvent createEmailClickedEvent() throws JsonProcessingException {
        ASEvent emailClickedEvent = new ASEvent()
                .withType(ASEvent.PRE.AS_CRM_MESSAGE_EMAIL_CLICKED)
                .withOccurredAt("2017-12-05T21:37:09.000Z")
                .withOrigin("mail.system")

                .withRelationIfValid(ASEventRelationTypes.ACTOR, "Email", "pera.detlic@gmail.com")
                .withRelationIfValid(ASEventRelationTypes.INVOLVES, "MailCampaign", "852456")
                .withRelationIfValid(ASEventRelationTypes.REFERENCES, "MarketingURL", "719181145")

                .withAspect(resolvable().withExternalId("10460811"))
                .withAspect(trafficSources().addTrafficSource(trafficSource().withType("campaign").withCampaign("MailCampaign/852456").withMedium("mail")))
                .withAspect(clientIP("127.0.0.1"));

        return emailClickedEvent;
    }

    public static ASEvent createEmailOpenedEvent() throws JsonProcessingException {
        ASEvent emailOpenedEvent = new ASEvent()
                .withType(ASEvent.PRE.AS_CRM_MESSAGE_EMAIL_OPENED)
                .withOccurredAt("2017-01-05T21:37:09.000Z")
                .withOrigin("mail.system")

                .withRelationIfValid(ASEventRelationTypes.ACTOR, "Email", "pera.detlic@gmail.com")
                .withRelationIfValid(ASEventRelationTypes.INVOLVES, "MailCampaign", "852456")

                .withAspect(resolvable().withExternalId("45834911"))
                .withAspect(trafficSources().addTrafficSource(trafficSource().withType("campaign").withCampaign("MailCampaign/852456").withMedium("mail")))
                .withAspect(clientIP("127.0.0.1"));

        return emailOpenedEvent;
    }

    public static ASEvent createEmailBouncedEvent() throws JsonProcessingException {
        ASEvent emailBouncedEvent = new ASEvent()
                .withType(ASEvent.PRE.AS_CRM_MESSAGE_EMAIL_BOUNCED)
                .withOccurredAt("2017-10-05T21:37:09.000Z")
                .withOrigin("mail.system")

                .withRelationIfValid(ASEventRelationTypes.ACTOR, "Email", "pera.detlic@gmail.com")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS, "MailCampaign", "852456")

                .withAspect(classification().withType("bounce").withVariant("soft"))
                .withAspect(resolvable().withExternalId("45834911"))
                .withAspect(trafficSources().addTrafficSource(trafficSource().withType("campaign").withCampaign("MailCampaign/852456").withMedium("mail")))
                .withAspect(clientIP("127.0.0.1"));

        return emailBouncedEvent;
    }

    public static ASEvent createEmailSubscribedEvent() throws JsonProcessingException {
        ASEvent emailSubscribedEvent = new ASEvent()
                .withType(ASEvent.PRE.AS_CRM_EMAIL_SUBSCRIBED)
                .withOccurredAt("2017-03-05T21:37:09.000Z")
                .withOrigin("mail.system")

                .withDimension("list_optin", "true")

                .withRelationIfValid(ASEventRelationTypes.ACTOR, "Email", "pera.detlic@gmail.com")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS, "MailingList", "2")

                .withAspect(resolvable().withExternalId("45834911"))
                .withAspect(clientIP("127.0.0.1"));

        return emailSubscribedEvent;
    }

    public static ASEvent createEmailUnsubscribedEvent() throws JsonProcessingException {
        ASEvent emailUnsubscribedEvent = new ASEvent()
                .withType(ASEvent.PRE.AS_CRM_EMAIL_UNSUBSCRIBED)
                .withOccurredAt("2017-11-05T21:37:09.000Z")
                .withOrigin("mail.system")

                .withDimension("list_optin", "true")

                .withRelationIfValid(ASEventRelationTypes.ACTOR, "Email", "pera.detlic@gmail.com")
                .withRelationIfValid(ASEventRelationTypes.AFFECTS, "MailingList", "2")

                .withAspect(resolvable().withExternalId("45834911"))
                .withAspect(clientIP("127.0.0.1"));

        return emailUnsubscribedEvent;
    }

    public static ASEntity createSubscriberEntity() throws JsonProcessingException {
        ASEntity subscriber = new ASEntity("MailSubscriber", "1159")

                .withDimension("status", "subscribed")
                .withDimension("status_code", "1")

                .withRelationIfValid(ASEntityRelationTypes.AKA, "Email", "pera.detlic@gmail.com")
                .withRelationIfValid(ASEntityRelationTypes.AKA, "Email", "petar.pera.detlic@gmail.com")

                .withAspect(presentation().withLabel("Petar Pera Detlic"))
                .withAspect(classification().withType("active"))
                .withAspect(timed().withPeriod("subscribed_on", "2017-07-23T20:30:09.000Z"))
                .withAspect(clientIP("127.0.0.1"));

        return subscriber;
    }

    public static ASEntity createEmailEntity() throws JsonProcessingException {
        ASEntity email = new ASEntity("Email", "pera.detlic@gmail.com")

                .withDimension("email_service", "gmail")
                .withDimension("status", "valid")

                .withRelationIfValid(ASEntityRelationTypes.PART_OF, "MailingList", "2")

                .withAspect(presentation().withLabel("Petar Pera Detlic"))
                .withAspect(classification().withType("private"));

        return email;
    }

    public static ASEntity createCampaignEntity() throws JsonProcessingException {
        ASEntity mailCampaign = new ASEntity("MailCampaign", "852456")

                .withDimension("content_type", "standard")
                .withDimension("is_test", "false")
                .withDimension("track_replies", "true")
                .withDimension("reply_to", "info@w3schools.com")

                .withRelationIfValid(ASEntityRelationTypes.AKA, "MailGoogleAnalyticsName", "glnm")
                .withRelationIfValid(ASEntityRelationTypes.BELONGS_TO, "MailingList", "2")
                .withRelationIfValid(ASEntityRelationTypes.BELONGS_TO, "MailingList", "36")
                .withRelationIfValid(ASEntityRelationTypes.PART_OF, "MailClient", "1093")

                .withAspect(content().withTitle("W3Schools ").withSubtitle("Tutorials").withContent("<!DOCTYPE html> <html> <body> <img src=\"w3schools.jpg\" alt=\"W3Schools.com\" width=\"104\" height=\"142\"> </body> </html>"))
                .withAspect(classification().withType("campaign").withVariant("standard"))
                .withAspect(presentation().withLabel("Learning with W3Schools"))

                .withProperties("status", "complete")
                .withProperties("authenticate", "false")
                .withProperties("show_in_archive", "false")
                .withProperties("view_in_browser", "false");

        return mailCampaign;
    }

    public static ASEntity createMailingListEntity() throws JsonProcessingException {
        ASEntity mailingList = new ASEntity("MailingList", "2")

                .withAspect(classification().withType("public"))
                .withAspect(presentation().withLabel("CSS Website Templates Newslatters"));

        return mailingList;
    }

    public static ASEntity createURLEntity() throws JsonProcessingException {
        ASEntity url = new ASEntity("MarketingURL", "714683")

                .withAspect(presentation().withLabel("W3.CSS Website Templates").withDetailsUrl("https://www.w3schools.com/w3css/w3css_templates.asp"));

        return url;
    }
}
