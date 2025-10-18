package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;

public class DynamicControlsPage {
    private final Page page;

    private final Locator checkbox;              // #checkbox (div z checkboxem)
    private final Locator removeAddButton;       // przycisk w sekcji checkboxa
    private final Locator message;               // #message

    private final Locator inputField;            // input w sekcji input-example
    private final Locator enableDisableButton;   // przycisk w sekcji input-example

    public DynamicControlsPage(Page page) {
        this.page = page;
        this.checkbox = page.locator("#checkbox");
        this.removeAddButton = page.locator("#checkbox-example button");
        this.message = page.locator("#message");
        this.inputField = page.locator("#input-example input");
        this.enableDisableButton = page.locator("#input-example button");
    }

    @Step("Kliknij przycisk Remove/Add")
    public void clickRemoveAddButton() {
        removeAddButton.click();
    }

    @Step("Czekaj aż checkbox zniknie (DETACHED)")
    public void waitForCheckboxToDisappear() {
        checkbox.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.DETACHED));
    }

    @Step("Czekaj aż checkbox się pojawi (ATTACHED → VISIBLE)")
    public void waitForCheckboxToAppear() {
        // najpierw pojawienie się w DOM…
        checkbox.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED));
        // …a następnie widoczność (zwykle szybkie, ale precyzyjne)
        checkbox.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
    }

    @Step("Kliknij przycisk Enable/Disable")
    public void clickEnableDisableButton() {
        enableDisableButton.click();
    }

    @Step("Czekaj aż input będzie aktywny")
    public void waitForInputToBeEnabled() {
        page.waitForFunction("sel => !document.querySelector(sel).disabled", "#input-example input");
    }

    @Step("Czekaj aż input będzie nieaktywny")
    public void waitForInputToBeDisabled() {
        page.waitForFunction("sel => document.querySelector(sel).disabled", "#input-example input");
    }

    @Step("Sprawdź czy input jest aktywny")
    public boolean isInputEnabled() {
        return inputField.isEnabled();
    }

    @Step("Sprawdź czy checkbox jest widoczny")
    public boolean isCheckboxVisible() {
        return checkbox.isVisible();
    }

    @Step("Pobierz wiadomość")
    public String getMessage() {
        return message.innerText().trim();
    }
}
