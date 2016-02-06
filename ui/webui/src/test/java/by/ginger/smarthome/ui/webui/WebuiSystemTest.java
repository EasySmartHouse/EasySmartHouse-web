/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui;

import by.ginger.smarthome.ui.webui.helpers.Order;
import by.ginger.smarthome.ui.webui.helpers.OrderedRunner;
import by.ginger.smarthome.ui.webui.page.LoginPage;
import by.ginger.smarthome.ui.webui.page.Page;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.*;

/**
 *
 * @author mirash
 */
@RunWith(OrderedRunner.class)
public class WebuiSystemTest {

    private static final int WAIT_IN_SEC = 60;
    private static WebDriver driver;
    private static Queue<Page> pages = new LinkedList<>();

    private static void initStartPage() {
        pages.add(new LoginPage(driver, Settings.BASE_URL));
    }

    @BeforeClass
    public static void before() throws Exception {
        driver = new FirefoxDriver();
        driver.manage()
                .timeouts()
                .implicitlyWait(WAIT_IN_SEC, TimeUnit.SECONDS);

        initStartPage();
    }

    @AfterClass
    public static void after() throws Exception {
        driver.quit();
    }

    @Test
    @Order(order = 1)
    public void testLogin() throws Exception {
        Page login = pages.poll();
        assertNotNull(login);
        pages.add(login.crawlAndGetNext());
    }

    @Test
    @Order(order = 2)
    public void testSignaling() throws Exception {
        Page control = pages.poll();
        assertNotNull(control);
        pages.add(control.crawlAndGetNext());
    }

    @Test
    @Order(order = 3)
    public void testMonitoring() throws Exception {
        Page control = pages.poll();
        assertNotNull(control);
        pages.add(control.crawlAndGetNext());
    }

    @Test
    @Order(order = 4)
    public void testLightControl() throws Exception {
        Page control = pages.poll();
        assertNotNull(control);
        pages.add(control.crawlAndGetNext());
    }

    @Test
    @Order(order = 5)
    public void testCameras() throws Exception {
        Page control = pages.poll();
        assertNotNull(control);
        pages.add(control.crawlAndGetNext());
    }

    @Test
    @Order(order = 6)
    public void testTriggers() throws Exception {
        Page control = pages.poll();
        assertNotNull(control);
        pages.add(control.crawlAndGetNext());
    }

    @Test
    @Order(order = 7)
    public void testLogout() throws Exception {
        Page control = pages.poll();
        assertNotNull(control);
        control.crawlAndGetNext();
    }
}
