package ui.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})
public interface SelenideConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://libertex.org/")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("version")
    @DefaultValue("128.0")
    String getBrowserVersion();

    @Key("remoteUrl")
    String getRemoteUrl();
}
