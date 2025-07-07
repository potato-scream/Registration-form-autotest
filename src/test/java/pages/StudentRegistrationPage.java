package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.CheckResultsComponent;
import utils.RandomUtils;

import java.nio.file.Paths;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static pages.components.CalendarComponent.fullDate;


public class StudentRegistrationPage {

    public RandomUtils randomUtils = new RandomUtils();

    @Step("Open student registration form page")
    public StudentRegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;
    }

    @Step("Remove banners and footer")
    public StudentRegistrationPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            phoneNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateSelect = $("#state"),
            stateWrapper = $("#stateCity-wrapper"),
            citySelect = $("#city"),
            cityWrapper = $("#stateCity-wrapper"),
            submitButton = $("#submit"),
            modalDialog = $(".modal-dialog"),
            modalTitle = $("#example-modal-sizes-title-lg");

    CalendarComponent calendarComponent = new CalendarComponent();
    CheckResultsComponent modalWindowComponent = new CheckResultsComponent();


    @Step("Set first name: {value}")
    public StudentRegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Set last name: {value}")
    public StudentRegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Set email: {value}")
    public StudentRegistrationPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public String selectedGender;

    @Step("Set random gender")
    public StudentRegistrationPage setGender() {
        selectedGender = randomUtils.getRandomGender();
        genderWrapper.$(byText(selectedGender)).click();

        return this;
    }

    @Step("Set phone number: {value}")
    public StudentRegistrationPage setPhoneNumber(String value) {
        phoneNumberInput.setValue(value);

        return this;
    }

    @Step("Set random date of birth")
    public StudentRegistrationPage setDateOfBirth() {
        calendarInput.scrollTo().click();
        calendarComponent.setRandomDate();

        return this;
    }

    @Step("Set address: {value}")
    public StudentRegistrationPage setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    public String selectedSubject;

    @Step("Set random subject")
    public StudentRegistrationPage setSubject() {
        selectedSubject = randomUtils.getRandomSubject();
        subjectsInput.setValue(selectedSubject).pressEnter();

        return this;
    }

    @Step("Set random hobby")
    public StudentRegistrationPage setHobby() {
        selectedHobby = randomUtils.getRandomHobby();
        hobbiesWrapper.$(byText(selectedHobby)).click();

        return this;
    }

    String selectedPicture = randomUtils.getRandomPicture();

    @Step("Upload random picture")
    public StudentRegistrationPage setPicture() {
        selectedPicture = randomUtils.getRandomPicture();
        uploadPicture.uploadFromClasspath(selectedPicture);

        return this;
    }
    private String selectedState;

    @Step("Set random state")
    public StudentRegistrationPage setState() {
        stateSelect.click();
        selectedState = randomUtils.getRandomState();
        stateWrapper.$(byText(selectedState)).click();
        return this;
    }

    public String selectedCity;

    @Step("Set random city")
    public StudentRegistrationPage setCity() {
        citySelect.click();
        selectedCity = randomUtils.getRandomCity(selectedState);
        cityWrapper.$(byText(selectedCity)).click();
        return this;
    }

    @Step("Verify subject result")
    public StudentRegistrationPage checkSubject() {
        checkResults("Subjects", selectedSubject);

        return this;
    }

    @Step("Verify result for '{key}': '{value}'")
    public StudentRegistrationPage checkResults(String key, String value) {
        modalWindowComponent.checkResults(key, value);

        return this;
    }

    @Step("Verify gender result")
    public StudentRegistrationPage checkGender() {
        checkResults("Gender", selectedGender);

        return this;
    }

    public String selectedHobby;

    @Step("Verify hobby result")
    public StudentRegistrationPage checkHobby() {
        checkResults("Hobbies", selectedHobby);

        return this;
    }

    @Step("Verify picture file name result")
    public StudentRegistrationPage checkPicture() {
        String fileName = Paths.get(selectedPicture).getFileName().toString();
        checkResults("Picture", fileName);
        return this;
    }

    @Step("Verify date of birth result")
    public StudentRegistrationPage checkDateOfBirth() {
        checkResults("Date of Birth", fullDate);

        return this;
    }

    @Step("Verify state and city result")
    public StudentRegistrationPage checkStateAndCity() {
        checkResults("State and City", selectedState + " " + selectedCity);

        return this;
    }

    @Step("Click 'Submit' button")
    public StudentRegistrationPage pressSubmit() {
        submitButton.click();

        return this;
    }

    @Step("Verify that the results modal is visible")
    public StudentRegistrationPage checkModalIsVisible() {
        $(modalDialog).should(appear);
        String modalSuccessTest = "Thanks for submitting the form";
        $(modalTitle).shouldHave(text(modalSuccessTest));

        return this;
    }

    public StudentRegistrationPage checkFieldIsInvalid(SelenideElement element) {
        element.should(Condition.match("has :invalid pseudoclass", el ->
                executeJavaScript("return getComputedStyle(arguments[0], ':invalid').display !== 'none';", el).equals(true)
        ));

        return this;
    }

    @Step("Verify that first name input is invalid")
    public StudentRegistrationPage checkFirstNameInputIsInvalid() {
        return checkFieldIsInvalid(firstNameInput);
    }

    @Step("Verify that last name input is invalid")
    public StudentRegistrationPage checkLastNameInputIsInvalid() {
        return checkFieldIsInvalid(lastNameInput);
    }

    @Step("Verify that phone number input is invalid")
    public StudentRegistrationPage checkUserNumberFieldIsInvalid() {
        checkFieldIsInvalid(phoneNumberInput);

        return this;
    }
}