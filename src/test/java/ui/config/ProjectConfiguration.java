package ui.config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class ProjectConfiguration {
    private final WebConfig webConfig;

    public ProjectConfiguration(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void webConfig() {
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser().toString();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 10000;
        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.remoteUrl();
            DesiredCapabilities options = new DesiredCapabilities();
            options.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "enableLog", true
            ));
            Configuration.browserCapabilities = options;
        }
    }

    public String getVideoStorageUrl() {
        return webConfig.videoStorage();
    }

    public Boolean isRemote() {
        return webConfig.isRemote();
    }
}
