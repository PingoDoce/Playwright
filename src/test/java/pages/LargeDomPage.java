package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LargeDomPage {
    private final Page page;

    public LargeDomPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate("https://the-internet.herokuapp.com/large");
    }

    public Locator getTableCell(int row, int col) {
        String cellId = String.format("sibling-%d\\.%d", row, col);
        return page.locator("#" + cellId);
    }

    public int countAllTableCells() {
        return page.locator("table td").count();
    }
}
