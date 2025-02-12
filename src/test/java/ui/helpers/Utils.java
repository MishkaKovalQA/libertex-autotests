package ui.helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.logging.LogType;

import static org.assertj.core.api.Assertions.assertThat;

@UtilityClass
public class Utils {

    public static String fetchBrowserLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(LogType.BROWSER));
    }

    public static void checkElementVisibilityByJavascript(String selector) {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();

        js.executeScript(
                "var element = document.querySelector('" + selector + "');" +
                        "element.style.pointerEvents = 'auto';" +
                        "var event = new MouseEvent('mouseover', {bubbles: true, cancelable: true, view: window});" +
                        "element.dispatchEvent(event);"
        );

        var isPresent = (Boolean) js.executeScript(
                "return document.querySelector('" + selector + "') !== null;"
        );

        var isVisible = (Boolean) js.executeScript(
                "var element = document.querySelector('" + selector + "');" +
                        "return (element && window.getComputedStyle(element).visibility === 'visible' && window.getComputedStyle(element).opacity !== '0');"
        );

        assertThat(isPresent).isTrue();
        assertThat(isVisible).isTrue();
    }
}
