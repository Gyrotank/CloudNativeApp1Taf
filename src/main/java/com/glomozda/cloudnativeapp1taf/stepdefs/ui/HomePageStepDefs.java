package com.glomozda.cloudnativeapp1taf.stepdefs.ui;

import com.glomozda.cloudnativeapp1taf.pages.HomePage;
import com.glomozda.cloudnativeapp1taf.properties.AppProperties;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class HomePageStepDefs {
    @Autowired
    private AppProperties appProperties;

    @Autowired
    private HomePage homePage;

    @When("User opens home page")
    public void userOpensHomePage() {
        String baseUrl = appProperties.getBaseUrl();
        homePage.open(baseUrl);
    }

    @Then("User sees main title")
    public void userSeesMainTitle() {
        homePage.waitForPageToLoad();
    }
}
