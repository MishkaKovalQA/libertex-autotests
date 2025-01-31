package ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.components.SubMenuComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends Page {

    public static final String TITLE = "Online Forex Trading | Trade For More | Trusted, Regulated Broker - Libertex";

    private final SelenideElement logo = $(".header__logo");
    private final SelenideElement loginButton = $(".nav-menu__link-login");
    private final SelenideElement signUpButton = $(".nav-menu__link-register");
    private final SelenideElement languageSwitcher = $(".nav-menu__link--languages");
    private final SelenideElement assetsWidget = $(".assets-widget-block__wrapper");
    private final SelenideElement platformsMenu = $("#header").$(byText("Platforms"));

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

    public SelenideElement getAssetsWidget() {
        return assetsWidget;
    }

    public SelenideElement getLanguageSwitcher() {
        return languageSwitcher;
    }

    @Step("Open platforms submenu")
    public SubMenuComponent openPlatformsSubmenu() {
        platformsMenu.click();
        return new SubMenuComponent($$(".nav-submenu.level-1").filterBy(visible).first());
    }

    @Step("Check that main page has the title = " + TITLE)
    public void checkTheTitle() {
        checkTheTitle(TITLE);
    }
}
