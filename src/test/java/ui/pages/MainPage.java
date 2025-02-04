package ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.components.SubMenuComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {

    public static final String TITLE = "Online Forex Trading | Trade For More | Trusted, Regulated Broker - Libertex";

    private final SelenideElement logo = $(".header__logo"),
                                  loginButton = $(".nav-menu__link-login"),
                                  signUpButton = $(".nav-menu__link-register"),
                                  languageSwitcher = $(".nav-menu__link--languages"),
                                  assetsWidget = $(".assets-widget-block__wrapper"),
                                  platformsMenu = $("#header").$(byText("Platforms"));

    @Step("Open main page")
    public MainPage openMainPage() {
        open(baseUrl);

        return this;
    }

    @Step("Open main page on language = {language}")
    public MainPage openMainPage(String language) {
        if (language.equals("en")) {
            open(baseUrl);
        } else {
            open(language);
        }

        return this;
    }

    @Step("Check logo of Libertex company is visible")
    public MainPage checkLogo() {
        logo.shouldBe(visible);

        return this;
    }

    @Step("Check login button is visible")
    public MainPage checkLoginButton() {
        loginButton.shouldBe(visible).shouldHave(text("Login"));
        return this;
    }

    @Step("Check sign up button is visible")
    public MainPage checkSignUpButton() {
        signUpButton.shouldBe(visible).shouldHave(text("Sign Up"));
        return this;
    }

    @Step("Check language switcher is visible")
    public MainPage checkLanguageSwitcher() {
        languageSwitcher.shouldBe(visible);

        return this;
    }

    @Step("Open platforms submenu")
    public SubMenuComponent openPlatformsSubmenu() {
        platformsMenu.click();
        return new SubMenuComponent($$(".nav-submenu.level-1").filterBy(visible).first());
    }

    @Step("Check that main page has the title = " + TITLE)
    public MainPage checkTitle() {
        var actualTitle = requireNonNull(title()).replaceAll("\\p{C}", "");
        assertThat(actualTitle).isEqualTo(TITLE);

        return this;
    }

    @Step("Check text for assets widget")
    public MainPage checkAssetsWidgetText(String expectedText) {
        assetsWidget.shouldHave(text(expectedText));

        return this;
    }
}
