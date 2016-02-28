/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.easysmarthouse.ui.webui.page;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author mirash
 */
public class IdlePage extends BasePage {

    public IdlePage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    @Override
    protected void crawl() throws Exception {
    }

    @Override
    protected Page getNext() throws Exception {
        return null;
    }

    @Override
    public String getRelative() {
        return "";
    }

}
