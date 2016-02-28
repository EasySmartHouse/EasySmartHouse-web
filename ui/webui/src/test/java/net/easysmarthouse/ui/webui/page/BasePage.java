/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.page;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author mirash
 */
abstract class BasePage implements Page {

    private static final int TIMEOUT_IN_SEC = 10;
    final WebDriver driver;
    final String baseUrl;

    protected final void validateCurrentPage() {
        if (!driver.getCurrentUrl().endsWith(this.getRelative())) {
            throw new IllegalStateException("Invalid page, current:"
                    + driver.getCurrentUrl()
                    + " expected: " + this.getRelative());
        }
    }

    protected void navigateToCurrentPage() {
        String currentUrl = driver.getCurrentUrl();
        String oldPage = FilenameUtils.getName(currentUrl);
        String newUrl = currentUrl.replaceFirst(oldPage, getRelative());

        driver.navigate().to(newUrl);
    }

    public BasePage(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
    }

    @Override
    public Page crawlAndGetNext() throws Exception {
        crawl();
        return getNext();
    }

    protected abstract void crawl() throws Exception;

    protected abstract Page getNext() throws Exception;

    void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")
                        .equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_IN_SEC);
        wait.until(pageLoadCondition);
    }

    public abstract String getRelative();
}
