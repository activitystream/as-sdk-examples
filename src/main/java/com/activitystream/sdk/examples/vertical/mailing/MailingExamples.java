package com.activitystream.sdk.examples.vertical.mailing;

import com.activitystream.core.model.aspects.ClassificationAspect;
import com.activitystream.core.model.aspects.ClientIpAspect;
import com.activitystream.core.model.aspects.ContentAspect;
import com.activitystream.core.model.aspects.PresentationAspect;
import com.activitystream.core.model.aspects.ResolvableAspect;
import com.activitystream.core.model.aspects.TimedAspect;
import com.activitystream.core.model.aspects.TrafficSource;
import com.activitystream.core.model.aspects.TrafficSourceAspect;
import com.activitystream.core.model.relations.ASEntityRelationTypes;
import com.activitystream.core.model.relations.ASEventRelationTypes;
import com.activitystream.sdk.ASEntity;
import com.activitystream.sdk.ASEvent;
import com.fasterxml.jackson.core.JsonProcessingException;

public class MailingExamples {

    public static ASEvent createEmailSentEvent() throws JsonProcessingException {
        ASEvent emailSentEvent = new ASEvent();
        emailSentEvent.withType(ASEvent.PRE.AS_CRM_MESSAGE_EMAIL_SENT);
        emailSentEvent.withOccurredAt("2017-02-05T21:37:09.000Z");
        emailSentEvent.withOrigin("mail.system");

        emailSentEvent.withRelationIfValid(ASEventRelationTypes.ACTOR, "MailCampaign", "852456");
        emailSentEvent.withRelationIfValid(ASEventRelationTypes.INVOLVES, "MailSubscriber", "1159");
        emailSentEvent.withRelationIfValid(ASEventRelationTypes.INVOLVES, "Email", "pera.detlic@gmail.com");

        emailSentEvent.withAspect(new ResolvableAspect().withExternalId("45834911"));
        emailSentEvent.withAspect(new ClientIpAspect().withIp("127.0.0.1"));

        return emailSentEvent;
    }

    public static ASEvent createEmailClickedEvent() throws JsonProcessingException {
        ASEvent emailClickedEvent = new ASEvent();
        emailClickedEvent.withType(ASEvent.PRE.AS_CRM_MESSAGE_EMAIL_CLICKED);
        emailClickedEvent.withOccurredAt("2017-12-05T21:37:09.000Z");
        emailClickedEvent.withOrigin("mail.system");

        emailClickedEvent.withRelationIfValid(ASEventRelationTypes.ACTOR, "Email", "pera.detlic@gmail.com");
        //        emailClickedEvent.withRelationIfValid(ASEventRelationTypes.ACTOR, "MailSubscriber", "1159"); TODO Option for Actor
        emailClickedEvent.withRelationIfValid(ASEventRelationTypes.INVOLVES, "MailCampaign", "852456");
        emailClickedEvent.withRelationIfValid(ASEventRelationTypes.REFERENCES, "MarketingURL", "719181145");

        emailClickedEvent.withAspect(new ResolvableAspect().withExternalId("10460811"));
        emailClickedEvent.withAspect(new TrafficSourceAspect().addTrafficSource(new TrafficSource().withType("campaign").withCampaign("MailCampaign/852456").withMedium("mail")));
        emailClickedEvent.withAspect(new ClientIpAspect().withIp("127.0.0.1"));

        return emailClickedEvent;
    }

    public static ASEvent createEmailOpenedEvent() throws JsonProcessingException {
        ASEvent emailOpenedEvent = new ASEvent();
        emailOpenedEvent.withType(ASEvent.PRE.AS_CRM_MESSAGE_EMAIL_OPENED);
        emailOpenedEvent.withOccurredAt("2017-01-05T21:37:09.000Z");
        emailOpenedEvent.withOrigin("mail.system");

        emailOpenedEvent.withRelationIfValid(ASEventRelationTypes.ACTOR, "Email", "pera.detlic@gmail.com");
        //        emailOpenedEvent.withRelationIfValid(ASEventRelationTypes.ACTOR, "MailSubscriber", "1159"); TODO Option for Actor
        emailOpenedEvent.withRelationIfValid(ASEventRelationTypes.INVOLVES, "MailCampaign", "852456");

        emailOpenedEvent.withAspect(new ResolvableAspect().withExternalId("45834911"));
        emailOpenedEvent.withAspect(new TrafficSourceAspect().addTrafficSource(new TrafficSource().withType("campaign").withCampaign("MailCampaign/852456").withMedium("mail")));
        emailOpenedEvent.withAspect(new ClientIpAspect().withIp("127.0.0.1"));

        return emailOpenedEvent;
    }

    public static ASEvent createEmailBouncedEvent() throws JsonProcessingException {
        ASEvent emailBouncedEvent = new ASEvent();
        emailBouncedEvent.withType(ASEvent.PRE.AS_CRM_MESSAGE_EMAIL_BOUNCED);
        emailBouncedEvent.withOccurredAt("2017-10-05T21:37:09.000Z");
        emailBouncedEvent.withOrigin("mail.system");

        emailBouncedEvent.withRelationIfValid(ASEventRelationTypes.ACTOR, "Email", "pera.detlic@gmail.com");
        emailBouncedEvent.withRelationIfValid(ASEventRelationTypes.AFFECTS, "MailCampaign", "852456");

        emailBouncedEvent.withAspect(new ClassificationAspect().withType("bounce").withVariant("soft"));
        emailBouncedEvent.withAspect(new ResolvableAspect().withExternalId("45834911"));
        emailBouncedEvent.withAspect(new TrafficSourceAspect().addTrafficSource(new TrafficSource().withType("campaign").withCampaign("MailCampaign/852456").withMedium("mail")));
        emailBouncedEvent.withAspect(new ClientIpAspect().withIp("127.0.0.1"));

        return emailBouncedEvent;
    }

    public static ASEvent createEmailSubscribedEvent() throws JsonProcessingException {
        ASEvent emailSubscribedEvent = new ASEvent();
        emailSubscribedEvent.withType(ASEvent.PRE.AS_CRM_EMAIL_SUBSCRIBED);
        emailSubscribedEvent.withOccurredAt("2017-03-05T21:37:09.000Z");
        emailSubscribedEvent.withOrigin("mail.system");

        emailSubscribedEvent.withDimension("list_optin", "true");

        emailSubscribedEvent.withRelationIfValid(ASEventRelationTypes.ACTOR, "Email", "pera.detlic@gmail.com");
        emailSubscribedEvent.withRelationIfValid(ASEventRelationTypes.AFFECTS, "MailingList", "2");

        emailSubscribedEvent.withAspect(new ResolvableAspect().withExternalId("45834911"));
        emailSubscribedEvent.withAspect(new ClientIpAspect().withIp("127.0.0.1"));

        return emailSubscribedEvent;
    }

    public static ASEvent createEmailUnsubscribedEvent() throws JsonProcessingException {
        ASEvent emailUnsubscribedEvent = new ASEvent();
        emailUnsubscribedEvent.withType(ASEvent.PRE.AS_CRM_EMAIL_UNSUBSCRIBED);
        emailUnsubscribedEvent.withOccurredAt("2017-11-05T21:37:09.000Z");
        emailUnsubscribedEvent.withOrigin("mail.system");

        emailUnsubscribedEvent.withDimension("list_optin", "true");

        emailUnsubscribedEvent.withRelationIfValid(ASEventRelationTypes.ACTOR, "Email", "pera.detlic@gmail.com");
        emailUnsubscribedEvent.withRelationIfValid(ASEventRelationTypes.AFFECTS, "MailingList", "2");

        emailUnsubscribedEvent.withAspect(new ResolvableAspect().withExternalId("45834911"));
        emailUnsubscribedEvent.withAspect(new ClientIpAspect().withIp("127.0.0.1"));

        return emailUnsubscribedEvent;
    }

    public static ASEntity createSubscriberEntity() throws JsonProcessingException { //TODO Check where to add it
        ASEntity subscriber = new ASEntity("MailSubscriber", "1159");

        subscriber.withDimension("status", "subscribed");
        subscriber.withDimension("status_code", "1");

        subscriber.withRelationIfValid(ASEntityRelationTypes.AKA, "Email", "pera.detlic@gmail.com");
        subscriber.withRelationIfValid(ASEntityRelationTypes.AKA, "Email", "petar.pera.detlic@gmail.com");

        subscriber.withAspect(new PresentationAspect("Petar Pera Detlic"));
        subscriber.withAspect(new ClassificationAspect("active"));
        subscriber.withAspect(new TimedAspect().withPeriod("subscribed_on", "2017-07-23T20:30:09.000Z"));
        subscriber.withAspect(new ClientIpAspect().withIp("127.0.0.1"));

        return subscriber;
    }

    public static ASEntity createEmailEntity() throws JsonProcessingException {
        ASEntity email = new ASEntity("Email", "pera.detlic@gmail.com");

        email.withDimension("email_service", "gmail");
        email.withDimension("status", "valid");

        email.withRelationIfValid(ASEntityRelationTypes.PART_OF, "MailingList", "2");

        email.withAspect(new PresentationAspect().withLabel("Petar Pera Detlic"));
        email.withAspect(new ClassificationAspect().withType("private"));

        return email;
    }

    public static ASEntity createCampaignEntity() throws JsonProcessingException {
        ASEntity mailCampaign = new ASEntity("MailCampaign", "852456");

        mailCampaign.withRelationIfValid(ASEntityRelationTypes.AKA, "MailGoogleAnalyticsName", "glnm");
        mailCampaign.withRelationIfValid(ASEntityRelationTypes.BELONGS_TO, "MailingList", "2");
        mailCampaign.withRelationIfValid(ASEntityRelationTypes.BELONGS_TO, "MailingList", "36");
        mailCampaign.withRelationIfValid(ASEntityRelationTypes.PART_OF, "MailClient", "1093");

        mailCampaign.withAspect(new ContentAspect().withTitle("W3Schools ").withSubtitle("Tutorials").withContent("<!DOCTYPE html> <html> <body> <img src=\"w3schools.jpg\" alt=\"W3Schools.com\" width=\"104\" height=\"142\"> </body> </html>"));
        mailCampaign.withAspect(new ClassificationAspect().withType("campaign").withVariant("standard"));
        mailCampaign.withAspect(new PresentationAspect().withLabel("Learning with W3Schools"));

        mailCampaign.withDimension("content_type", "standard");
        mailCampaign.withDimension("is_test", "false");
        mailCampaign.withDimension("track_replies", "true");
        mailCampaign.withDimension("reply_to", "info@w3schools.com");

        mailCampaign.withProperties("status", "complete");
        mailCampaign.withProperties("authenticate", "false");
        mailCampaign.withProperties("show_in_archive", "false");
        mailCampaign.withProperties("view_in_browser", "false");

        return mailCampaign;
    }

    public static ASEntity createMailingListEntity() throws JsonProcessingException {
        ASEntity mailingList = new ASEntity("MailingList", "2");

        mailingList.withAspect(new ClassificationAspect().withType("public"));
        mailingList.withAspect(new PresentationAspect().withLabel("CSS Website Templates Newslatters"));

        return mailingList;
    }

    public static ASEntity createURLEntity() throws JsonProcessingException {
        ASEntity url = new ASEntity("MarketingURL", "714683");

        url.withAspect(new PresentationAspect().withLabel("W3.CSS Website Templates").withDetailsUrl("https://www.w3schools.com/w3css/w3css_templates.asp"));

        return url;
    }
}
