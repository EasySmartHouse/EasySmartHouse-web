/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.page;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class CamerasPage extends BasePage {

    public CamerasPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    @Override
    protected void crawl() throws Exception {
        navigateToCurrentPage();

        this.validateCurrentPage();
        this.waitForLoad();

        List<WebElement> camerasView = driver.findElements(By.cssSelector(
                "#webcams > .webcam > img"));
        assertTrue(camerasView.size() > 0);
    }

    @Override
    protected Page getNext() throws Exception {
        return new TriggersPage(driver, baseUrl);
    }

    @Override
    public String getRelative() {
        return "webui.html#cameras";
    }
}