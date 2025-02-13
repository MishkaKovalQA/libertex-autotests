package ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.config.ConfigReader;
import ui.config.ProjectConfiguration;
import ui.config.WebConfig;
import ui.constants.Browser;
import ui.helpers.Attachments;
import ui.pages.careers.CareersPage;
import ui.pages.main.MainPage;
import ui.pages.careers.JobApplicationPage;
import ui.pages.careers.JobOverviewPage;
import ui.pages.main.com.MainComPage;

@ExtendWith({BrowserPerTestStrategyExtension.class})
@Owner("m.koval")
public class TestBase {

    protected MainPage mainPage = new MainPage();
    protected MainComPage mainComPage = new MainComPage();
    protected CareersPage careersPage = new CareersPage();
    protected JobOverviewPage jobOverviewPage = new JobOverviewPage();
    protected JobApplicationPage jobApplicationPage = new JobApplicationPage();

    private static final String ENV = System.getProperty("env", "local");

    private static final WebConfig webConfig = ConfigReader.Instance.read();
    private static final ProjectConfiguration PROJECT = new ProjectConfiguration(webConfig);

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        PROJECT.webConfig();
    }

    @AfterEach
    void tearDown() {
        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        if (Configuration.browser.equals(Browser.CHROME.name())) {
            Attachments.browserConsoleLogs();
        }
        if (PROJECT.isRemote()) {
            Attachments.addVideo(PROJECT.getVideoStorageUrl());
        }
    }
}
