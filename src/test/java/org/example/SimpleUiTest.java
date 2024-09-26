package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.element;

public class SimpleUiTest {
    @Test
    public void userCanNotCreateAccountWithNameAndSurnameOnly(){
        Selenide.open("https://parabank.parasoft.com/parabank/register.htm");

        SelenideElement firstName = element(Selectors.byId("customer.firstName"));
        firstName.sendKeys("Иван");

        SelenideElement lastName = element(Selectors.byId("customer.lastName"));
        lastName.sendKeys("Иванов");

        SelenideElement registerButton = element(Selectors.byValue("Register"));
        registerButton.click();

        //Проверка ожидаемого результата
        SelenideElement addressError = element(Selectors.byId("customer.address.street.errors"));
        addressError.shouldHave(Condition.exactText("Address is required."));

        SelenideElement cityError = element(Selectors.byId("customer.address.city.errors"));
        cityError.shouldHave(Condition.exactText("City is required."));

        SelenideElement stateError = element(Selectors.byId("customer.address.state.errors"));
        stateError.shouldHave(Condition.exactText("State is required."));

        SelenideElement zipCodeError = element(Selectors.byId("customer.address.zipCode.errors"));
        zipCodeError.shouldHave(Condition.exactText("Zip Code is required."));

        SelenideElement ssnError = element(Selectors.byId("customer.ssn.errors"));
        ssnError.shouldHave(Condition.exactText("Social Security Number is required."));

        SelenideElement usernameError = element(Selectors.byId("customer.username.errors"));
        usernameError.shouldHave(Condition.exactText("Username is required."));

        SelenideElement passwordError = element(Selectors.byId("customer.password.errors"));
        passwordError.shouldHave(Condition.exactText("Password is required."));

        SelenideElement repeatedPasswordError = element(Selectors.byId("repeatedPassword.errors"));
        repeatedPasswordError.shouldHave(Condition.exactText("Password confirmation is required."));
    }

    @Test
    public void userCanCreateAccount() {
        Selenide.open("https://parabank.parasoft.com/parabank/register.htm");

        SelenideElement firstName = element(Selectors.byId("customer.firstName"));
        firstName.sendKeys("Astarion");

        SelenideElement lastName = element(Selectors.byId("customer.lastName"));
        lastName.sendKeys("Ancunin");

        SelenideElement address = element(Selectors.byId("customer.address.street"));
        address.sendKeys("Baldurs");

        SelenideElement city = element(Selectors.byId("customer.address.city"));
        city.sendKeys("Gate");

        SelenideElement state = element(Selectors.byId("customer.address.state"));
        state.sendKeys("Ravaged Beach");

        SelenideElement zipCode = element(Selectors.byId("customer.address.zipCode"));
        zipCode.sendKeys("123456");

        SelenideElement ssn = element(Selectors.byId("customer.ssn"));
        ssn.sendKeys("123456");

        String usernameValue = "username";
        SelenideElement username = element(Selectors.byId("customer.username"));
        username.sendKeys("AstarionXXXX");

        SelenideElement password = element(Selectors.byId("customer.password"));
        password.sendKeys("123456Ff");

        SelenideElement repeatedPassword = element(Selectors.byId("repeatedPassword"));
        repeatedPassword.sendKeys("123456Ff");

        SelenideElement registerButton = element(Selectors.byValue("Register"));
        registerButton.click();

        SelenideElement welcome = element(Selectors.byClassName("title"));
        welcome.shouldHave(Condition.text("Welcome "));


    }
}

