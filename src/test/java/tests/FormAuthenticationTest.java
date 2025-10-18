package tests;

import org.junit.jupiter.api.Test;
import pages.FormAuthenticationPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormAuthenticationTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        logStep("Go to Login page");
        page.navigate("https://the-internet.herokuapp.com/login");

        FormAuthenticationPage loginPage = new FormAuthenticationPage(page);

        logStep("Enter correct credentials");
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        logStep("Verify successful login");
        String message = loginPage.getFlashMessage();
        assertTrue(message.contains("You logged into a secure area!"));
    }

    @Test
    public void testInvalidLogin() {
        logStep("Go to Login page");
        page.navigate("https://the-internet.herokuapp.com/login");

        FormAuthenticationPage loginPage = new FormAuthenticationPage(page);

        logStep("Enter incorrect credentials");
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLogin();

        logStep("Verify error message");
        String message = loginPage.getFlashMessage();
        assertTrue(message.contains("Your username is invalid!"));
    }
}
