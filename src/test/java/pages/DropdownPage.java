package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import io.qameta.allure.Step;

public class DropdownPage {
    private final Page page;
    private final Locator dropdown;

    public DropdownPage(Page page) {
        this.page = page;
        this.dropdown = page.locator("#dropdown");
    }

    @Step("Wybierz opcję po widocznym tekście: {optionText}")
    public void selectByVisibleText(String optionText) {
        dropdown.selectOption(new SelectOption().setLabel(optionText));
    }

    @Step("Pobierz aktualnie wybraną opcję")
    public String getSelectedOptionText() {
        return page.locator("#dropdown option:checked").innerText();
    }
}
