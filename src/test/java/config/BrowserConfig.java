package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:ui.properties"
})
public interface BrowserConfig extends Config {
    @Key("browser") @DefaultValue("chrome")
    String getBrowser();

    @Key("browser.version") @DefaultValue("")
    String getBrowserVersion();

    @Key("browser.size") @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("base.url") @DefaultValue("https://demoqa.com")
    String getBaseUrl();

    @Key("headless") @DefaultValue("false")
    boolean isHeadless();
}
