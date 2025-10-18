package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ForgotPasswordPage {
    private final Page page;
    private final Locator emailInput;
    private final Locator retrieveButton;
    private final Locator confirmationMessage;

    public ForgotPasswordPage(Page page) {
        this.page = page;
        this.page.navigate("https://the-internet.herokuapp.com/forgot_password");
        this.emailInput = page.locator("#email");
        this.retrieveButton = page.locator("#form_submit");
        this.confirmationMessage = page.locator("h1");
    }

    public void enterEmail(String email) {
        emailInput.fill(email);
    }

    public void clickRetrievePassword() {
        retrieveButton.click(); // Playwright poczeka na nawigację jeśli wystąpi
    }

    public String getConfirmationMessage() {
        confirmationMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return confirmationMessage.innerText();
    }
}
