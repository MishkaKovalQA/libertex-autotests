package ui.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Selenide.$;

@RequiredArgsConstructor
public class CookieConsentComponent {

    private final SelenideElement cookieConsentAcceptButton = $("[data-ui=cookie-consent-accept]");

    @Step("Accept cookie")
    public void acceptCookie() {
        cookieConsentAcceptButton.click();
    }
}
