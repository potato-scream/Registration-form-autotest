package demoqa;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationPage;
import utils.RandomUtils;

import static io.qameta.allure.Allure.step;

import org.junit.jupiter.api.DisplayName;
public class StudentRegistrationFormTest extends TestBase {

    StudentRegistrationPage studentRegistrationPage = new StudentRegistrationPage();
    RandomUtils randomUtils = new RandomUtils();

    @Test
    @Tag("demoqa")
    @DisplayName("Successful student registration with all fields")
    void successfulStudentRegistrationTest() {

        String firstName = randomUtils.firstName;
        String lastName = randomUtils.lastName;
        String userEmail = randomUtils.userEmail;
        String phoneNumber = randomUtils.phoneNumber;
        String streetAddress = randomUtils.streetAddress;

        step("Open page and remove banners", () -> {
            studentRegistrationPage.openPage()
                    .removeBanner();
        });

        step("Fill in all fields of the form", () -> {
            studentRegistrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(userEmail)
                    .setGender()
                    .setPhoneNumber(phoneNumber)
                    .setSubject()
                    .setDateOfBirth()
                    .setHobby()
                    .setPicture()
                    .setAddress(streetAddress)
                    .setState()
                    .setCity();
        });

        step("Click the 'Submit' button", () -> {
            studentRegistrationPage.pressSubmit();
        });

        step("Verify the data in the final modal window", () -> {
            studentRegistrationPage.checkModalIsVisible()
                    .checkResults("Student Name", firstName + " " + lastName)
                    .checkResults("Student Email", userEmail)
                    .checkGender()
                    .checkResults("Mobile", phoneNumber)
                    .checkDateOfBirth()
                    .checkSubject()
                    .checkHobby()
                    .checkPicture()
                    .checkResults("Address", streetAddress)
                    .checkStateAndCity();
        });
    }

    @Test
    @DisplayName("Student registration with only required fields")
    void registrationWithRequiredFieldsTest() {

        String firstName = randomUtils.firstName;
        String lastName = randomUtils.lastName;
        String phoneNumber = randomUtils.phoneNumber;

        step("Open page and fill in only required fields", () -> {
            studentRegistrationPage.openPage()
                    .removeBanner()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setGender()
                    .setPhoneNumber(phoneNumber);
        });

        step("Click the 'Submit' button", () -> {
            studentRegistrationPage.pressSubmit();
        });

        step("Verify the data in the final modal window", () -> {
            studentRegistrationPage.checkModalIsVisible()
                    .checkResults("Student Name", firstName + " " + lastName)
                    .checkGender()
                    .checkResults("Mobile", phoneNumber);
        });
    }

    @Test
    @DisplayName("Check validation for empty required fields")
    void checkValidationForEmptyRequiredFieldsTest() {
        step("Open page and click 'Submit' without filling in the fields", () -> {
            studentRegistrationPage.openPage()
                    .removeBanner()
                    .pressSubmit();
        });

        step("Verify that the fields are highlighted as invalid", () -> {
            studentRegistrationPage.checkFirstNameInputIsInvalid()
                    .checkLastNameInputIsInvalid()
                    .checkUserNumberFieldIsInvalid();
        });
    }

    @Test
    @DisplayName("Check validation for an incomplete phone number")
    void checkValidationForShortPhoneNumberTest() {
        step("Open page, enter an incomplete phone number, and click 'Submit'", () -> {
            studentRegistrationPage.openPage()
                    .removeBanner()
                    .setPhoneNumber(randomUtils.invalidPhoneNumber)
                    .pressSubmit();
        });

        step("Verify that the phone number field is highlighted as invalid", () -> {
            studentRegistrationPage.checkUserNumberFieldIsInvalid();
        });
    }
}