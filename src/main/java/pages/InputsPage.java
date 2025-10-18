package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InputsPage {
    private final Page page;
    private final Locator inputField;

    public InputsPage(Page page) {
        this.page = page;
        this.inputField = page.locator("input");
    }

    public void open() {
        page.navigate("https://the-internet.herokuapp.com/inputs");
    }

    public void setInputValue(String value) {
        inputField.fill(""); // czyści pole
        inputField.type(value);
    }

    public String getInputValue() {
        return inputField.inputValue();
    }
}

