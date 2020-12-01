package com.udacity.jwdnd.course1.cloudstorage;

import lombok.Data;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class ResultPage {

    @FindBy(id = "success-continue-link")
    private WebElement redirectToHomeLink;

    @FindBy(id = "success-alert")
    private WebElement successAlert;

    @FindBy(id = "error-alert")
    private WebElement errorAlert;

    private JavascriptExecutor jsExecutor;

    public ResultPage(WebDriver driver, JavascriptExecutor jsExecutor) {
        PageFactory.initElements(driver, this);
        this.jsExecutor = jsExecutor;
    }

    void redirectToHome() {
        jsExecutor.executeScript("arguments[0].click();", redirectToHomeLink);
    }

}
