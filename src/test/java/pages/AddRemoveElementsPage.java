package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class AddRemoveElementsPage {
    private final Page page;

    private final Locator addElementButton;
    private final Locator deleteButtons;

    public AddRemoveElementsPage(Page page) {
        this.page = page;
        this.addElementButton = page.locator("//button[text()='Add Element']");
        this.deleteButtons = page.locator("button.added-manually");
    }

    @Step("Kliknij przycisk 'Add Element'")
    public void clickAddElement() {
        addElementButton.click();
    }

    @Step("Kliknij przycisk 'Delete' o indeksie {index}")
    public void clickDeleteButton(int index) {
        if (index < deleteButtons.count()) {
            deleteButtons.nth(index).click();
        }
    }

    @Step("Pobierz liczbę przycisków 'Delete'")
    public int getDeleteButtonsCount() {
        return deleteButtons.count();
    }
}
