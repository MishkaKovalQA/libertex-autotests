package ui.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import ui.pages.careers.CareersPage;
import ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    MainPage mainPage = new MainPage();
    CareersPage careersPage = new CareersPage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://libertex.org/";
        Configuration.browserCapabilities.setCapability("pageLoadStrategy", "eager");
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 30000;
        Configuration.timeout = 10000;
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
