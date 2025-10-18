package tests;

import org.junit.jupiter.api.Test;
import pages.ForgotPasswordPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForgotPasswordTest extends BaseTest {

    @Test
    public void testForgotPasswordFormSubmission() {
        logStep("Open Forgot Password page");
        ForgotPasswordPage pageObject = new ForgotPasswordPage(page);

        logStep("Fill in email and submit the form");
        pageObject.enterEmail("test@example.com");
        pageObject.clickRetrievePassword();

        logStep("Verify result");
        String text = pageObject.getConfirmationMessage();
        assertTrue(text.contains("Internal Server Error"),
                "Expected error message to be shown, but got: " + text);
    }
}
