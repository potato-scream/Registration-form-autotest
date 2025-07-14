//package demoqa;
//
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.logevents.SelenideLogger;
//import io.qameta.allure.selenide.AllureSelenide;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import helpers.Attach;
//
//import java.util.Map;
//
//public class TestBase {
//    @BeforeAll
//    static void beforeAll() {
//        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.browser = System.getProperty("browser", "chrome");
//        String user = System.getProperty("user");
//        String password = System.getProperty("password");
//        String selenoidUrl = System.getProperty("selenoidUrl");
//        String fullUrl = "https://" + user + ":" + password + "@" + selenoidUrl;
//        if (selenoidUrl != null && !selenoidUrl.isEmpty()) {
//            Configuration.remote = fullUrl;
//        }
//
//        Configuration.browserVersion = System.getProperty("version", null);
//        Configuration.browserSize = System.getProperty("size","1920x1080");
//
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        Configuration.browserCapabilities = capabilities;
//        Configuration.holdBrowserOpen = true;
//
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
//    }
//
//    @AfterEach
//    void addAttachments() {
//        Attach.screenshotAs("Last screenshot");
//        Attach.pageSource();
//        Attach.browserConsoleLogs();
//        Attach.addVideo();
//    }
//}

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

        // Remote driver setup
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        // ИЗМЕНЕНО: Временно закомментировали DesiredCapabilities для отладки.
        // Запускаем тест с минимальной конфигурацией, без запроса VNC и видео.
        /*
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        */

        // Allure listener
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        // ИЗМЕНЕНО: Временно закомментировали аттач видео, так как мы его не записываем.
        // Attach.addVideo();
    }
}


