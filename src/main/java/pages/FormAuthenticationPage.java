package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FormAuthenticationPage {

    private final Page page;

    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator flashMessage;

    public FormAuthenticationPage(Page page) {
        this.page = page;
        this.usernameField = page.locator("#username");
        this.passwordField = page.locator("#password");
        this.loginButton = page.locator("button.radius");
        this.flashMessage = page.locator("#flash");
    }

    public void enterUsername(String username) {
        usernameField.fill(username);
    }

    public void enterPassword(String password) {
        passwordField.fill(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String getFlashMessage() {
        flashMessage.waitFor(); // domyślnie czeka aż będzie widoczny
        return flashMessage.innerText().trim();
    }
}
