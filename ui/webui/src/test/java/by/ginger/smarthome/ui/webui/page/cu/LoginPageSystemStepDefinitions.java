/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.page.cu;

import by.ginger.smarthome.ui.webui.Settings;
import by.ginger.smarthome.ui.webui.page.LoginPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author mirash
 */
public class LoginPageSystemStepDefinitions {

    private WebDriver driver;
    private LoginPage page;

    @Before
    public void prepare() {
        driver = new FirefoxDriver();

        page = new LoginPage(driver, Settings.BASE_URL);
        driver.get(Settings.BASE_URL + page.getRelative());
    }

    @After
    public void cleanUp() {
        driver.close();
    }

    @Given("^user pass his name '(.*)' and password '(.*)'$")
    public void userLogin(final String name, final String password) {
        page.login(name, password);
    }

    @When("^the user press 'Submit' button$")
    public void submit() {
        page.submit();
    }

    @Then("^the message should be shown$")
    public void assertTheMessage(String message) {
        page.checkErrorMessage(message);
    }
}
