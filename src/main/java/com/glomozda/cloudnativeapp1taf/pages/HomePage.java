package com.glomozda.cloudnativeapp1taf.pages;

import com.glomozda.cloudnativeapp1taf.stepdefs.Hooks;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class HomePage extends BasePage {
    private static final String SUFFIX = "home";

    @FindBy(xpath = "//div[contains(@class, 'text-center')]")
    WebElement title;

    public void waitForPageToLoad() {
        PageFactory.initElements(Hooks.getWebDriver(), this);
        waitForElement(title);
    }

    @Override
    public void open(final String baseUrl) {
        super.open(baseUrl + SUFFIX);
    }
}
