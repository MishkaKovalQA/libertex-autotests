package ui.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import ui.config.SelenideConfigProvider;
import ui.helpers.Attachments;
import ui.pages.careers.CareersPage;
import ui.pages.main.MainPage;
import ui.pages.careers.JobApplicationPage;
import ui.pages.careers.JobOverviewPage;
import ui.pages.main.com.MainComPage;

import static com.codeborne.selenide.Selenide.*;
import static ui.constants.Tags.REGRESS_TESTS;

@Tag(REGRESS_TESTS)
public class TestBase {

    protected MainPage mainPage = new MainPage();
    protected MainComPage mainComPage = new MainComPage();
    protected CareersPage careersPage = new CareersPage();
    protected JobOverviewPage jobOverviewPage = new JobOverviewPage();
    protected JobApplicationPage jobApplicationPage = new JobApplicationPage();

    private static final String ENV = System.getProperty("env", "local");

    @BeforeAll
    static void setUp() {
        new SelenideConfigProvider();

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void tearDown() {
        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        if (ENV.equals("remote")) {
            Attachments.addVideo();
        }

        closeWebDriver();
    }
}
