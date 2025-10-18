package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HoversPage {
    private final Page page;
    private final Locator figures;
    private final Locator captions;

    public HoversPage(Page page) {
        this.page = page;
        this.figures = page.locator(".figure");
        this.captions = page.locator(".figcaption");
    }

    public void hoverOverFigure(int index) {
        figures.nth(index).hover();
    }

    public String getCaptionText(int index) {
        return captions.nth(index).innerText().trim();
    }
}
