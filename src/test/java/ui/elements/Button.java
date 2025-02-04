package ui.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.visible;

@Slf4j
@RequiredArgsConstructor
public class Button {

    private final String name;
    private final SelenideElement selector;

    @Step("Click button {this.name}")
    public void click() {
        log.info(("Click button " + name));
        selector.click();
    }

    @Step("Is button {this.name} visible = {isVisible}")
    public Button isVisible(boolean isVisible) {
        if (isVisible) {
            selector.shouldBe(visible);
            log.info(("Button " + name + " is visible"));
        } else {
            selector.shouldNotBe(visible);
            log.info(("Button " + name + " is not visible"));
        }

        return this;
    }
}
