package demoqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import utils.RandomUtils;

import static io.qameta.allure.Allure.step;

public class TextBoxTest extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();
    RandomUtils randomUtils = new RandomUtils();

    String fullName = randomUtils.firstName + " " + randomUtils.lastName;
    String email = randomUtils.userEmail;
    String currentAddress = randomUtils.streetAddress;

    @Test
    @DisplayName("Successful fill of the Text Box form")
    void successfulFillTextBoxFormTest() {

        step("Open the page and fill in the fields", () -> {
            textBoxPage.openPage()
                    .removeBanner()
                    .setName(fullName)
                    .setEmail(email)
                    .setCurrentAddress(currentAddress)
                    .setPermanentAddress(currentAddress);
        });

        step("Click the 'Submit' button", () -> {
            textBoxPage.pressSubmit();
        });

        step("Verify that the data in the output table matches the entered data", () -> {
            textBoxPage.checkResults("name", fullName)
                    .checkResults("email", email)
                    .checkResults("currentAddress", currentAddress)
                    .checkResults("permanentAddress", currentAddress);
        });
    }
}