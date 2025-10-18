package pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class RedirectorPage {
    private final Page page;

    public RedirectorPage(Page page) {
        this.page = page;
    }

    @Step("Kliknij link 'here'")
    public void clickHereLink() {
        page.locator("text=here").click();
    }

    @Step("Pobierz aktualny URL")
    public String getCurrentUrl() {
        return page.url();
    }
}
