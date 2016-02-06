/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.page;

import java.util.List;
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
public class TriggersPage extends BasePage {

    private static final int STATUS_CHANGE_WAIT_IN_SEC = 3;

    public TriggersPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    private void checkTriggerStatus(String status, String xpath) {
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

        List<WebElement> triggerCheckBoxes = driver.findElements(
                By.cssSelector("#triggersView .gwt-CheckBox > input"));
        assertNotNull(triggerCheckBoxes);
        assertTrue(triggerCheckBoxes.size() > 0);

        WebElement triggerCheckBox = triggerCheckBoxes.get(0);
        triggerCheckBox.click();
        
        this.checkTriggerStatus("Deactivated", "id('triggersView')/div/table/tbody/tr[2]/td[3]");
    }

    @Override
    protected Page getNext() throws Exception {
        return new LogoutPage(driver, baseUrl);
    }

    @Override
    public String getRelative() {
        return "webui.html#trigger";
    }
}
