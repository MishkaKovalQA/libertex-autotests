package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ui.constants.BrowserErrorLevel.ERROR;
import static ui.constants.Tags.*;
import static ui.helpers.Utils.fetchBrowserLogs;

@Tags({@Tag(REGRESS_COM_TESTS), @Tag(MAIN_COM_PAGE_TESTS)})
class MainComPageTests extends TestBase {

    @Tag(SMOKE_TESTS)
    @Test
    @DisplayName("Check all main page elements (logo, buttons, language switcher, etc.)")
    void mainPageShouldHaveCorrectMainElementsTest() {
        mainComPage.openMainPage()
                .checkLogo()
                .checkTitle()
                .checkSignUpButton()
                .checkLoginButton()
                .checkLanguageSwitcher();

        assertThat(fetchBrowserLogs())
                .as("Browser logs doesn't contain any errors")
                .doesNotContain(ERROR.name());
    }

    @Test
    @DisplayName("Check platforms in header")
    public void platformsCheckTest() {
        var platformItems = List.of("Libertex", "MetaTrader 4", "MetaTrader 5");

        mainComPage.openMainPage()
                .openPlatformsSubmenu()
                .checkItemsVisible(platformItems);
    }

    @ParameterizedTest
    @DisplayName("Check change language via URL for menu")
    @CsvSource({
            "en, Platforms",
            "de, Plattformen",
            "fr, Platformes",
            "it, Piattaforme",
            "nl, Platforms",
            "pl, Platformy",
            "pt, Plataformas",
    })
    void checkMenuTranslationWhenChangeUrlTest(String language, String expectedText) {
        mainComPage.openMainPage(language)
                .checkTranslationInMenuText(expectedText);
    }
}
