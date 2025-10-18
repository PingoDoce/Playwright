package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;


public class DynamicLoadingPage {
    private final Page page;

    private final Locator startButton;
    private final Locator loading;
    private final Locator result;

    public DynamicLoadingPage(Page page) {
        this.page = page;
        this.startButton = page.locator("#start button");
        this.loading = page.locator("#loading");
        this.result = page.locator("#finish");
    }

    @Step("Kliknij Start")
    public void clickStart() {
        startButton.click();
    }

    @Step("Poczekaj na zniknięcie wskaźnika ładowania")
    public void waitForLoadingToFinish() {
        loading.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }

    @Step("Pobierz wynikowy tekst")
    public String getResultText() {
        result.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return result.innerText().trim();
    }
}
