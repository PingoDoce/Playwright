package pages;

import com.microsoft.playwright.Page;

public class JavaScriptAlertsPage {
    private final Page page;

    public JavaScriptAlertsPage(Page page) {
        this.page = page;
    }

    public void open() {
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
    }

    public void triggerJsAlert() {
        page.locator("//button[text()='Click for JS Alert']").click();
    }

    public void triggerJsConfirm() {
        page.locator("//button[text()='Click for JS Confirm']").click();
    }

    public void triggerJsPrompt() {
        page.locator("//button[text()='Click for JS Prompt']").click();
    }

    public String getResultText() {
        return page.locator("#result").innerText().trim();
    }

    public void acceptAlert() {
        page.onceDialog(dialog -> dialog.accept());
    }

    public void dismissAlert() {
        page.onceDialog(dialog -> dialog.dismiss());
    }

    public void sendTextToPrompt(String text) {
        page.onceDialog(dialog -> dialog.accept(text));
    }
}
