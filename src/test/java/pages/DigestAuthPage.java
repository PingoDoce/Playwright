package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class DigestAuthPage {
    private final Page page;
    private final Locator successMessage;

    public DigestAuthPage(Page page) {
        this.page = page;
        this.successMessage = page.locator("p");
    }

    @Step("Pobierz komunikat po zalogowaniu")
    public String getSuccessMessage() {
        return successMessage.innerText();
    }
}
