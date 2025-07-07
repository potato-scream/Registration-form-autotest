package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TextBoxPage {
    public TextBoxPage openPage() {
        open("/text-box");
        $("h1").shouldHave(text("Text Box"));

        return this;
    }

    public TextBoxPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    private final SelenideElement fullNameInput = $("#userName"),
            emailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitButton = $("#submit");

    public TextBoxPage setName(String value) {
        fullNameInput.setValue(value);

        return this;
    }
    public TextBoxPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public TextBoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage pressSubmit() {
        submitButton.click();

        return this;
    }

    public TextBoxPage checkResults(String key, String value) {
        $("#output").$(byId(key)).shouldHave(text(value));

        return this;
    }

}
