package ui.config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SelenideConfigProvider {

    private final SelenideConfig config;

    public static final String ENV = System.getProperty("env", "local");

    public SelenideConfigProvider() {
        this.config = ConfigFactory.create(SelenideConfig.class, System.getProperties());
        configureSelenide();
    }

    private void configureSelenide() {
        switch (config.getBrowser().toLowerCase()) {
            case "firefox":
                Configuration.browser = "firefox";
                break;
            case "chrome":
            default:
                Configuration.browser = "chrome";
        }

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.remote = config.getRemoteUrl();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.pageLoadStrategy = "eager";
        Configuration.pageLoadTimeout = 30000;
        Configuration.timeout = 10000;

        if (ENV.equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);

            Configuration.browserCapabilities = capabilities;
        }
    }
}
