package demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import helpers.Attach;

import java.util.Map;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        // Basic browser setup
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("version", "120.0");
        Configuration.browserSize = System.getProperty("size","1920x1080");
        Configuration.pageLoadStrategy = "eager";

        // Remote driver setup that reads from Jenkins parameters
        String selenoidUrl = System.getProperty("selenoidUrl");
        if (selenoidUrl != null && !selenoidUrl.isEmpty()) {
            String user = System.getProperty("user");
            String password = System.getProperty("password");
            Configuration.remote = "https://" + user + ":" + password + "@" + selenoidUrl;
        }

        // Selenoid capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        // Allure listener
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}



