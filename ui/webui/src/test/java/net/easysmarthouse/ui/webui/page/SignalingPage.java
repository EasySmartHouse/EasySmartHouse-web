/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
public class SignalingPage extends BasePage {

    private static final int STATUS_CHANGE_WAIT_IN_SEC = 3;

    public SignalingPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    private void checkSignalingStatusChange(String checkBoxSelector, String statusSelector) throws Exception {
        WebElement checkBoxElement = driver.findElement(By.cssSelector(checkBoxSelector));
        if (checkBoxElement.isSelected()) {
            checkBoxElement.click();
        }

        final WebElement statusElement = driver.findElement(By.cssSelector(statusSelector));
        final String statusText = statusElement.getText();

        Thread checkThread = new Thread() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        assertTrue("Status has changed", statusElement.getText().equals(statusText));
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }
        };
        checkThread.start();
        checkThread.join(STATUS_CHANGE_WAIT_IN_SEC * 1000);
        checkThread.interrupt();
    }

    @Override
    protected void crawl() throws Exception {
        this.waitForLoad();

        checkSignalingStatusChange(
                ".tab-grid > tbody > tr:nth-child(3) > td:nth-child(4) > .gwt-CheckBox input",
                ".tab-grid > tbody > tr:nth-child(3) > td:nth-child(3)");
    }

    @Override
    protected Page getNext() throws Exception {
        return new MonitoringPage(driver, baseUrl);
    }

    @Override
    public String getRelative() {
        return "webui.html#signaling";
    }
}
