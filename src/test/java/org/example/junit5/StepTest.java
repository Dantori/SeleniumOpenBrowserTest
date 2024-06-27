package org.example.junit5;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StepTest {

    private static final String GLOBAL_PARAMETER = "global value";

    @Test
    public void annotatedStepTest() {
        annotatedStep("local value");
    }

    @Test
    public void lambdaStepTest() {
        final String localParameter = "parameter value";
        Allure.step(String.format("Parent lambda step with parameter [%s]", localParameter), (step) -> {
            step.parameter("parameter", localParameter);
            Allure.step(String.format("Nested lambda step with global parameter [%s]", GLOBAL_PARAMETER));
        });
    }

    @Step("Parent annotated step with parameter [{parameter}]")
    public void annotatedStep(final String parameter) {
        nestedAnnotatedStep();
    }

    @Step("Nested annotated step with global parameter [{this.GLOBAL_PARAMETER}]")
    public void nestedAnnotatedStep() {

    }

    @Test
    void openBrowserTest() {
        Configuration.browser = "firefox";
        open("https://www.google.com");
        SelenideElement searchBox = $(By.name("q"));
        searchBox.setValue("Selenide").pressEnter();
        $("#search").shouldHave(text("selenide.org"));
    }
}
