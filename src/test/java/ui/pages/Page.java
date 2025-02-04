package ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

public class Page {

    protected final SelenideElement cookieConsentAcceptButton = $("[data-ui=cookie-consent-accept]");
    private final SelenideElement page = $("#page");

    @Step("Accept cookie")
    public Page acceptCookie() {
        cookieConsentAcceptButton.click();

        return this;
    }

    @Step("Check that page contains text = {pageText}")
    protected void checkTextOnPage(String pageText) {
        page.shouldHave(text(pageText));
    }

    protected void checkTheTitle(String expectedTitle) {
            var actualTitle = requireNonNull(title()).replaceAll("\\p{C}", "");
            assertThat(actualTitle).isEqualTo(expectedTitle);
    }
}
