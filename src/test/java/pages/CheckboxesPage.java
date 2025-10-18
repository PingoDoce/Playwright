package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class CheckboxesPage {
    private final Page page;
    private final Locator checkboxes;

    public CheckboxesPage(Page page) {
        this.page = page;
        this.checkboxes = page.locator("#checkboxes input[type='checkbox']");
    }

    @Step("Pobierz wszystkie checkboxy")
    public Locator getAllCheckboxes() {
        return checkboxes;
    }

    @Step("Sprawdź, czy checkbox o indeksie {index} jest zaznaczony")
    public boolean isCheckboxSelected(int index) {
        return checkboxes.nth(index).isChecked();
    }

    @Step("Zaznacz checkbox o indeksie {index}")
    public void checkCheckbox(int index) {
        Locator checkbox = checkboxes.nth(index);
        if (!checkbox.isChecked()) {
            checkbox.check();
        }
    }

    @Step("Odznacz checkbox o indeksie {index}")
    public void uncheckCheckbox(int index) {
        Locator checkbox = checkboxes.nth(index);
        if (checkbox.isChecked()) {
            checkbox.uncheck();
        }
    }
}
