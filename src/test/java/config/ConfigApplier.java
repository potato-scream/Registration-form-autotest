package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.Objects;

import static helpers.Attach.attachAsText;

public class ConfigApplier {
    private final BrowserConfig config = ConfigFactory.create(BrowserConfig.class, System.getProperties());

    public void applyConfig() {
        Configuration.browser = config.getBrowser();
        if (config.getBrowserVersion() != null && !config.getBrowserVersion().isEmpty()) {
            Configuration.browserVersion = config.getBrowserVersion();
        }
        Configuration.browserSize = config.getBrowserSize();
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.pageLoadStrategy = "eager";

        String remoteUrl = config.getRemoteUrl();
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            String user = config.getRemoteUser();
            String password = config.getRemotePassword();
            Configuration.remote = "https://" + user + ":" + password + "@" + remoteUrl;

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }

        attachAppliedConfiguration();
    }

    private void attachAppliedConfiguration() {
        String remoteInfo = Configuration.remote != null ? "\nremote.url = " + Configuration.remote : "";
        attachAsText("Примененная конфигурация:",
                "\nbrowser = " + Configuration.browser +
                        "\nbrowser.version = " + Configuration.browserVersion +
                        "\nbrowser.size = " + Configuration.browserSize +
                        "\nbaseUrl = " + Configuration.baseUrl +
                        remoteInfo);
    }
}
