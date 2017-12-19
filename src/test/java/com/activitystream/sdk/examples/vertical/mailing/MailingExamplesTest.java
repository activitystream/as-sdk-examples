package com.activitystream.sdk.examples.vertical.mailing;

import com.activitystream.sdk.ASEntity;
import com.activitystream.sdk.ASEvent;
import com.activitystream.sdk.ASService;
import net.javacrumbs.jsonunit.JsonAssert;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.TimeZone;

public class MailingExamplesTest {

    private static final String BASE_PATH = "src/test/resources/test-data.mailing/";

    @BeforeMethod
    public void setUp() {
        //Sets predefined parameters such as country code, currency and time zone which applies to all messages. It is executed before test cases.
        ASService.setDefaults("", "", TimeZone.getTimeZone("GMT+0:00"));
    }

    @Test
    public void testCreateEmailSentEvent() throws Exception {
        ASEvent emailSent = MailingExamples.createEmailSentEvent();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "emailSent-sample.json"), "UTF-8"), emailSent.toJSON());
    }

    @Test
    public void testCreateEmailClickedEvent() throws Exception {
        ASEvent emailClicked = MailingExamples.createEmailClickedEvent();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "emailClicked-sample.json"), "UTF-8"), emailClicked.toJSON());
    }

    @Test
    public void testCreateEmailOpenedEvent() throws Exception {
        ASEvent emailOpened = MailingExamples.createEmailOpenedEvent();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "emailOpened-sample.json"), "UTF-8"), emailOpened.toJSON());
    }

    @Test
    public void testCreateEmailBouncedEvent() throws Exception {
        ASEvent emailBounced = MailingExamples.createEmailBouncedEvent();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "emailBounced-sample.json"), "UTF-8"), emailBounced.toJSON());
    }

    @Test
    public void testCreateEmailSubscribedEvent() throws Exception {
        ASEvent emailSubscribed = MailingExamples.createEmailSubscribedEvent();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "emailSubscribed-sample.json"), "UTF-8"), emailSubscribed.toJSON());
    }

    @Test
    public void testCreateEmailUnsubscribedEvent() throws Exception {
        ASEvent emailUnsubscribed = MailingExamples.createEmailUnsubscribedEvent();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "emailUnsubscribed-sample.json"), "UTF-8"), emailUnsubscribed.toJSON());
    }

    @Test
    public void testCreateSubscriberEntity() throws Exception {
        ASEntity subscriber = MailingExamples.createSubscriberEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "subscriber-sample.json"), "UTF-8"), subscriber.toJSON());
    }

    @Test
    public void testCreateEmailEntity() throws Exception {
        ASEntity email = MailingExamples.createEmailEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "email-sample.json"), "UTF-8"), email.toJSON());
    }

    @Test
    public void testCreateCampaignEntity() throws Exception {
        ASEntity campaign = MailingExamples.createCampaignEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "mailCampaign-sample.json"), "UTF-8"), campaign.toJSON());
    }

    @Test
    public void testCreateMailingListEntity() throws Exception {
        ASEntity mailingList = MailingExamples.createMailingListEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "mailingList-sample.json"), "UTF-8"), mailingList.toJSON());
    }

    @Test
    public void testCreateURLEntity() throws Exception {
        ASEntity url = MailingExamples.createURLEntity();

        JsonAssert.assertJsonEquals(FileUtils.readFileToString(new File(BASE_PATH + "marketingURL-sample.json"), "UTF-8"), url.toJSON());
    }
}