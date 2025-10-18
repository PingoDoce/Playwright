package pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FramesPage {
    private final Page page;

    public FramesPage(Page page) {
        this.page = page;
    }

    public void openNestedFrames() {
        page.navigate("https://the-internet.herokuapp.com/nested_frames");
    }

    public String getTextFromMiddleFrame() {
        FrameLocator middle = page
                .frameLocator("frame[name='frame-top']")
                .frameLocator("frame[name='frame-middle']");
        return middle.locator("#content").innerText();
    }

    public void openIFramePage() {
        page.navigate("https://the-internet.herokuapp.com/iframe");
    }

    public String getTextFromIFrame() {
        FrameLocator editorFrame = page.frameLocator("iframe#mce_0_ifr");
        return editorFrame.locator("#tinymce").innerText();
    }

    public void setTextInIFrame(String newText) {
        FrameLocator editorFrame = page.frameLocator("iframe#mce_0_ifr");
        Locator editor = editorFrame.locator("#tinymce");
        editor.click();
        editor.press("Control+A");
        editor.type(newText);
    }
}
