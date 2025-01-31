package ui.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import ui.pages.MainPage;

public class TestBase {

    MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://libertex.org/";
        Configuration.browserCapabilities.setCapability("pageLoadStrategy", "eager");
        Configuration.pageLoadTimeout = 30000;
        Configuration.timeout = 10000;
    }
}
