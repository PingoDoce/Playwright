package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.MouseButton;
import io.qameta.allure.Step;

public class ContextMenuPage {
    private final Page page;
    private final Locator hotSpot;

    public ContextMenuPage(Page page) {
        this.page = page;
        this.hotSpot = page.locator("#hot-spot");
    }

    @Step("Wykonaj kliknięcie prawym przyciskiem myszy na pole")
    public void rightClickHotSpot() {
        hotSpot.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
    }
}
