package ui.pages.main.com;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.components.SubMenuComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static ui.helpers.Utils.checkElementVisibilityByJavascript;

public class MainComPage {

    public static final String TITLE = "Libertex Trading Platform | FOREX | CFDs | CRYPTO | Libertex.com";

    private final SelenideElement logo = $(".header__logo"),
            header = $("#header"),
            languageSwitcher = $(".nav-menu__link--languages"),
            platformsMenu = header.$(byText("Platforms"));

    @Step("Open main page")
    public MainComPage openMainPage() {
        open("https://libertex.com/start");

        return this;
    }

    @Step("Open main page on language = {language}")
    public MainComPage openMainPage(String language) {
        switch (language) {
            case "en" -> open("https://libertex.com/start");
            case "de" -> open("https://libertex.com/" + language + "/start");
            default -> open("https://libertex.com/" + language);
        }

        return this;
    }

    @Step("Check logo of Libertex company is visible")
    public MainComPage checkLogo() {
        logo.shouldBe(visible);

        return this;
    }

    @Step("Check login button is appeared")
    public MainComPage checkLoginButton() {
        checkElementVisibilityByJavascript(".cta-registration-header__login");

        return this;
    }

    @Step("Check sign up button is appeared")
    public MainComPage checkSignUpButton() {
        checkElementVisibilityByJavascript("[data-icid-id=register_top_navigation_menu]");

        return this;
    }

    @Step("Check language switcher is visible")
    public MainComPage checkLanguageSwitcher() {
        languageSwitcher.shouldBe(visible);

        return this;
    }

    @Step("Open platforms submenu")
    public SubMenuComponent openPlatformsSubmenu() {
        platformsMenu.click();
        return new SubMenuComponent($$(".nav-submenu.level-1").filterBy(visible).first());
    }

    @Step("Check that main page has the title = " + TITLE)
    public MainComPage checkTitle() {
        var actualTitle = requireNonNull(title()).replaceAll("\\p{C}", "");
        assertThat(actualTitle).isEqualToIgnoringCase(TITLE);

        return this;
    }

    @Step("Check translation for menu")
    public MainComPage checkTranslationInMenuText(String expectedText) {
        header.shouldHave(text(expectedText));

        return this;
    }
}
