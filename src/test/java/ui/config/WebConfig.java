package ui.config;

import org.aeonbits.owner.Config;
import ui.constants.Browser;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${env}.properties",
        "file:~/${env}.properties",
        "file:./${env}.properties"
})
public interface WebConfig extends Config {

    @Key("browser")
    @DefaultValue("CHROME")
    Browser browser();

    @Key("browserVersion")
    @DefaultValue("133.0")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("baseUrl")
    @DefaultValue("https://libertex.org/")
    String baseUrl();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteUrl")
    @DefaultValue("http://localhost:4444")
    String remoteUrl();

    @Key("videoStorage")
    String videoStorage();
}
