package com.glomozda.cloudnativeapp1taf.stepdefs.api;

import com.glomozda.cloudnativeapp1taf.properties.AppProperties;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

@Log4j2
@SpringBootTest
public class ApiStepDefs {
    RequestSpecification requestSpecification;
    Response response;

    @Autowired
    private AppProperties appProperties;

    @When("User sends GET request to {string}")
    public void userSendsGetRequestToEndpoint(final String endpoint) {
        String baseUrl = appProperties.getBaseUrl();
        requestSpecification = new RequestSpecBuilder().setBaseUri(baseUrl).build();
        response = given().spec(requestSpecification)
                .log().all()
                .get(endpoint)
                .then()
                .log().status()
                .extract().response();
    }

    @Then("User receives status code of {int}")
    public void userReceivesStatusCode(final int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("Response body contains text: {string}")
    public void responseBodyContainsText(final String text) {
        response.then().body(containsString(text));
    }
}
