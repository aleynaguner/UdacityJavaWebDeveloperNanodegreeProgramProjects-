package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(css="#inputUsername")
    private WebElement inputUsername;

    @FindBy(css="#inputPassword")
    private WebElement inputPassword;

    @FindBy(css="#loginError")
    private WebElement loginError;

    @FindBy(css="#logoutStatus")
    private WebElement logoutStatus;

    @FindBy(css="#submitButton")
    private WebElement submitButton;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        this.inputUsername.sendKeys(username);
        this.inputPassword.sendKeys(password);
        this.submitButton.click();
    }

    public boolean loginError(){
        return loginError.isDisplayed();
    }

    public boolean logoutStatus(){
        return logoutStatus.isDisplayed();
    }

}
