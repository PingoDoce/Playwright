package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class KeyPressesPage {
    private final Page page;
    private final Locator inputField;
    private final Locator resultText;

    public KeyPressesPage(Page page) {
        this.page = page;
        this.inputField = page.locator("#target");
        this.resultText = page.locator("#result");
    }

    public void open() {
        page.navigate("https://the-internet.herokuapp.com/key_presses");
    }

    public void typeKey(String key) {
        inputField.click();
        // Jeśli pojedynczy znak – wpisz; w innym przypadku użyj press (np. Escape/Shift/Alt/Tab/Space)
        if (key != null && key.length() == 1) {
            inputField.type(key);
        } else {
            inputField.press(key);
        }
    }

    public String getResult() {
        return resultText.innerText().trim();
    }
}
