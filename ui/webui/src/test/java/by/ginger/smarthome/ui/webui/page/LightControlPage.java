/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.page;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author mirash
 */
public class LightControlPage extends BasePage {

    private static final int STATUS_CHANGE_WAIT_IN_SEC = 3;

    public LightControlPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    private void checkSwitchStatus(String status, String xpath) {
        WebElement statusElement = driver.findElement(By.xpath(xpath));
        assertNotNull(statusElement);

        (new WebDriverWait(driver, STATUS_CHANGE_WAIT_IN_SEC)).until(
                ExpectedConditions.textToBePresentInElement(
                statusElement,
                status));
    }

    @Override
    protected void crawl() throws Exception {
        navigateToCurrentPage();
        
        this.validateCurrentPage();
        this.waitForLoad();

        List<WebElement> toggleButtons = driver.findElements(
                By.className("gwt-Button"));
        assertNotNull(toggleButtons);
        assertTrue(toggleButtons.size() > 0);

        WebElement toggleBtn = toggleButtons.get(0);
        toggleBtn.click();
        
        this.checkSwitchStatus("Off", "id('actuatorsView')/div/table/tbody/tr[2]/td[4]");
    }

    @Override
    protected Page getNext() throws Exception {
        return new CamerasPage(driver, baseUrl);
    }

    @Override
    public String getRelative() {
        return "webui.html#switching";
    }
}
