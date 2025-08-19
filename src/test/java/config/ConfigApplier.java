package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;

import static helpers.Attach.attachAsText;

public class ConfigApplier {
    private final BrowserConfig config = ConfigFactory.create(BrowserConfig.class, System.getProperties());

    public void applyConfig() {
        Configuration.browser = config.getBrowser();
        if (!config.getBrowserVersion().isEmpty()) {
            Configuration.browserVersion = config.getBrowserVersion();
        }
        Configuration.browserSize = config.getBrowserSize();
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.pageLoadStrategy = "eager";

        attachAppliedConfiguration();
    }

    private void attachAppliedConfiguration() {
        attachAsText("Applied configuration:",
                "\nbrowser = " + Configuration.browser +
                        "\nbrowser.version = " + Configuration.browserVersion +
                        "\nbrowser.size = " + Configuration.browserSize +
                        "\nbaseUrl = " + Configuration.baseUrl);
    }
}
