package ui.helpers;

import com.codeborne.selenide.Selenide;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.logging.LogType;

@UtilityClass
public class Utils {

    public static String fetchBrowserLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(LogType.BROWSER));
    }
}
