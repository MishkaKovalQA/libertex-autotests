package ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import ui.helpers.Attachments;
import ui.pages.careers.CareersPage;
import ui.pages.MainPage;
import ui.pages.careers.JobApplicationPage;
import ui.pages.careers.JobOverviewPage;

import static com.codeborne.selenide.Selenide.*;
import static ui.constants.Tags.REGRESS_TESTS;

@Tag(REGRESS_TESTS)
class TestBase {

    MainPage mainPage = new MainPage();
    CareersPage careersPage = new CareersPage();
    JobOverviewPage jobOverviewPage = new JobOverviewPage();
    JobApplicationPage jobApplicationPage = new JobApplicationPage();

    private static final String ENVIRONMENT = System.getProperty("env");

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://libertex.org/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 30000;
        Configuration.timeout = 10000;

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void tearDown() {
        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        if (ENVIRONMENT.equals("remote")) {
            Attachments.addVideo();
        }

        closeWebDriver();
    }
}
