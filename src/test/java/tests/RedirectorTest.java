package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.RedirectorPage;
import utils.BaseTest;

public class RedirectorTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test przekierowania po kliknięciu w link 'here'")
    public void testRedirect() {
        page.navigate("https://the-internet.herokuapp.com/redirector");

        RedirectorPage pageObject = new RedirectorPage(page);
        pageObject.clickHereLink();

        // Oczekujemy, że po przekierowaniu trafimy na /status_codes
        String redirectedUrl = pageObject.getCurrentUrl();
        Assertions.assertTrue(redirectedUrl.contains("/status_codes"),
                "URL po przekierowaniu powinien zawierać '/status_codes'");
    }
}
