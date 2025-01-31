package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

public class LanguageTests extends TestBase {

    @ParameterizedTest
    @DisplayName("Check change language from English to Spanish via URL")
    @CsvSource({
            "en, Trading Assets",
            "es, Operación con activos"
    })
    void changeLanguageWithUrlTest(String language, String expectedText) {
        mainPage.openMainPage(language);

        step("Check translated text for assets widget in " + language, () -> {
            mainPage.getAssetsWidget().shouldHave(text(expectedText));
        });
    }

    @Test
    @DisplayName("Check language switcher visibility")
    void checkLanguageSwitcherVisibility() {
        mainPage.openMainPage();

        step("Check that language switcher is visible", () -> {
            mainPage.getLanguageSwitcher().shouldBe(visible);
        });
    }
}
