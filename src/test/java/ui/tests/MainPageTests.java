package ui.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;
import static ui.constants.BrowserErrorLevel.ERROR;
import static ui.helpers.Utils.fetchBrowserLogs;

public class MainPageTests extends TestBase {

    @Test
    @DisplayName("Check the title and logo of main page")
    void titleTest() {
        mainPage.openMainPage()
                .checkLogo()
                .checkTheTitle();
    }

    @Test
    @DisplayName("Check that login and sign up buttons are correct")
    void signUpAndLoginButtonsShouldBeVisibleTest() {
        mainPage.openMainPage()
                .checkSignUpButton()
                .checkLoginButton();
    }

    @Test
    @DisplayName("Check that there is no errors on the main page")
    void consoleShouldNotHaveErrorsTest() {
        mainPage.openMainPage();
        $(".assets-widget-block").shouldBe(visible);

        assertThat(fetchBrowserLogs())
                .as("Browser logs doesn't contain any errors")
                .doesNotContain(ERROR.name());
    }

    @Test
    @DisplayName("Check platforms in header")
    public void platformsCheckTest() {
        var platformItems = List.of("Libertex", "MetaTrader 4", "MetaTrader 5");

        mainPage.openMainPage()
                .openPlatformsSubmenu()
                .checkItemsVisible(platformItems);
    }

    @AfterEach
    void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
