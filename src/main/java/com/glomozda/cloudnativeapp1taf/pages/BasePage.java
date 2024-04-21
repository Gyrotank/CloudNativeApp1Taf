package com.glomozda.cloudnativeapp1taf.pages;

import com.glomozda.cloudnativeapp1taf.stepdefs.Hooks;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Lazy
public abstract class BasePage {
    protected static final Duration WAIT_IN_SECONDS = Duration.ofSeconds(10);

    protected void open(final String baseUrl) {
        openURL(baseUrl);
    }

    private void openURL(final String url) {
        Hooks.getWebDriver().get(url);
    }

    protected void waitForElement(final WebElement element) {
        try {
            waitForElementToBePresent(element);
        } catch (StaleElementReferenceException e) {
            PageFactory.initElements(Hooks.getWebDriver(), this);
            waitForElementToBePresent(element);
        }
    }

    private void waitForElementToBePresent(final WebElement element) {
        new WebDriverWait(Hooks.getWebDriver(), WAIT_IN_SECONDS).until(ExpectedConditions.visibilityOf(element));
    }
}
