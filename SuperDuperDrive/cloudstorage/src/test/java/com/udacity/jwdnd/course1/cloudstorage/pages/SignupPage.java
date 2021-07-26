package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(css = "#inputFirstName")
    private WebElement inputFirstName;

    @FindBy(css = "#inputLastName")
    private WebElement inputLastName;

    @FindBy(css = "#inputUsername")
    private WebElement inputUsername;

    @FindBy(css = "#inputPassword")
    private WebElement inputPassword;

    @FindBy(css = "#submitButton")
    private WebElement submitButton;

    @FindBy(id = "signupSuccess")
    private WebElement signupSuccess;

    @FindBy(id = "signupError")
    private WebElement signupError;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstName, String lastName, String username, String password) {
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        submitButton.click();
    }

    public boolean signupSuccess(){
        return signupSuccess.isDisplayed();
    }

    public boolean signupError(){
        return signupError.isDisplayed();
    }

}
