/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class LogoutPage extends BasePage {

    public LogoutPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    @Override
    protected void crawl() throws Exception {
        WebElement logoutLink = driver.findElement(By.cssSelector("#logout"));
        assertNotNull(logoutLink);

        logoutLink.click();
        this.waitForLoad();
    }

    @Override
    protected Page getNext() throws Exception {
        return new IdlePage(driver, baseUrl);
    }

    @Override
    public String getRelative() {
        return "webui.html";
    }
}
