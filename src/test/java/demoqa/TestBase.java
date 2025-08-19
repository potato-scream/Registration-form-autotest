package demoqa;

import config.ConfigApplier;
import helpers.Attach;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        new ConfigApplier().applyConfig();
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .includeSelenideSteps(true)
                        .screenshots(false)
                        .savePageSource(false)
        );
    }

    @AfterEach
    void attachArtifacts() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
