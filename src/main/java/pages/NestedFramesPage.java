package pages;

import com.microsoft.playwright.Page;

public class NestedFramesPage {
    private final Page page;

    public NestedFramesPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate("https://the-internet.herokuapp.com/nested_frames");
    }
}
