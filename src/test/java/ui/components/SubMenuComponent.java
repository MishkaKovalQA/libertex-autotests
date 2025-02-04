package ui.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class SubMenuComponent {

    private final SelenideElement submenu;

    public SubMenuComponent(SelenideElement submenu) {
        this.submenu = submenu;
    }

    @Step("Check that submenu contains items with texts: {texts}")
    public SubMenuComponent checkItemsVisible(List<String> texts) {
        texts.forEach(text ->
                submenu.$$(".nav-submenu__item")
                        .findBy(text(text))
                        .shouldBe(visible)
        );

        return this;
    }
}
