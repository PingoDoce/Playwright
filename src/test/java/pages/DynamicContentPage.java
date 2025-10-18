package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.util.List;

public class DynamicContentPage {
    private final Page page;
    private final Locator contentBlocks;

    public DynamicContentPage(Page page) {
        this.page = page;
        this.contentBlocks = page.locator("#content > .row");
    }

    @Step("Pobierz wszystkie bloki dynamicznej treści")
    public Locator getAllContentBlocks() {
        return contentBlocks;
    }

    @Step("Pobierz zawartość tekstową każdego bloku")
    public List<String> getAllTexts() {
        return contentBlocks.allTextContents().stream()
                .map(String::trim)
                .toList();
    }
}
