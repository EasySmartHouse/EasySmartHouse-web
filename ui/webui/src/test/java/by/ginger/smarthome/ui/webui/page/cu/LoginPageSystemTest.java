/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.ginger.smarthome.ui.webui.page.cu;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * @author mirash
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
        format = {"pretty", "html:target/cucumber"},
        features = "src/test/resources/features")
public class LoginPageSystemTest {
}
