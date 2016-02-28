/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.page;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class MonitoringPage extends BasePage {

    public MonitoringPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    private int getSensorsCount() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return Integer.valueOf(js.executeScript(
                "return document.getElementsByClassName('table-striped')[0].getElementsByTagName('tr').length;").toString());
    }

    @Override
    protected void crawl() throws Exception {
        navigateToCurrentPage();

        this.validateCurrentPage();
        this.waitForLoad();

        int sensorsCount = getSensorsCount();

        for (int i = 2; i < sensorsCount + 1; i++) {
            WebElement statusElement = driver.findElement(By.cssSelector(
                    ".table-striped > tbody > tr:nth-child(" + i + ") > td:nth-child(3)"));
            String value = statusElement.getText();
            assertNotEquals("0.0", value);
        }
    }

    @Override
    protected Page getNext() throws Exception {
        return new LightControlPage(driver, baseUrl);
    }

    @Override
    public String getRelative() {
        return "webui.html#monitoring";
    }
}
