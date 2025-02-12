package ui.tests.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ui.tests.TestBase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ui.constants.BrowserErrorLevel.ERROR;
import static ui.constants.Tags.MAIN_PAGE_TESTS;
import static ui.constants.Tags.SMOKE_TESTS;
import static ui.helpers.Utils.fetchBrowserLogs;

@Tag(MAIN_PAGE_TESTS)
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
