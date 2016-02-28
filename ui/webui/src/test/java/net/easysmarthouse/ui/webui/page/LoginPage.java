/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class LoginPage extends BasePage {

    private static final int MSG_WAIT_IN_SEC = 3;

    public LoginPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public void login(String login, String pass) {
        WebElement userField = driver.findElement(By.cssSelector(".form-login .form-control:nth-of-type(1)"));
        userField.sendKeys(login);

        WebElement passField = driver.findElement(By.cssSelector(".form-login .form-control:nth-of-type(2)"));
        passField.sendKeys(pass);
    }

    public void submit() {
        driver.findElement(By.cssSelector(".btn-block")).click();
    }

    public void checkErrorMessage(String msgExpected) {
        WebElement msgElement = driver.findElement(By.cssSelector(".gwt-HTML"));
        assertNotNull(msgElement);

        (new WebDriverWait(driver, MSG_WAIT_IN_SEC)).until(
                ExpectedConditions.textToBePresentInElement(
                msgElement,
                msgExpected));
    }

    @Override
    protected Page getNext() throws Exception {
        return new SignalingPage(driver, baseUrl);
    }

    @Override
    protected void crawl() throws Exception {
        driver.get(baseUrl + getRelative());

        this.validateCurrentPage();

        this.login("rusakovich", "12345678");
        submit();
    }

    @Override
    public String getRelative() {
        return "login.html";
    }
}
