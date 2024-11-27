package com.example.selenium;
import org.openqa.selenium.By;

public class PromethousLocators {
    public static final By CONTACT_US= By.linkText("Contact Us");
    public static final By FIRST_NAME= By.name("firstname");
    public static final By LAST_NAME= By.name("lastname");
    public static final By SUBMIT_BUTTON= By.cssSelector(".hs-button.primary.large");
    public static final By REQUIRED_FIELD_WARNING= By.xpath("//*[contains(text(),'Please complete this required field.')]");


}
