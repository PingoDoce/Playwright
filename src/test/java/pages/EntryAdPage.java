package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class EntryAdPage {
    private final Page page;
    private final Locator modal;
    private final Locator modalTitle;
    private final Locator closeModalButton;
    private final Locator clickHereLink;

    public EntryAdPage(Page page) {
        this.page = page;
        this.modal = page.locator("#modal");
        this.modalTitle = page.locator(".modal-title");
        this.closeModalButton = page.locator(".modal-footer p");
        this.clickHereLink = page.locator("a:has-text(\"click here\")");
    }

    public void open() {
        page.navigate("https://the-internet.herokuapp.com/entry_ad");
    }

    public void waitForModalToAppear() {
        try {
            modal.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10_000));
        } catch (RuntimeException e) {
            System.out.println("Modal nie pojawił się w oczekiwanym czasie.");
        }
    }

    public void closeModal() {
        closeModalButton.click();
        modal.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }

    public boolean isModalVisible() {
        return modal.isVisible();
    }

    public String getModalTitle() {
        return modalTitle.innerText();
    }

    public void restartModal() {
        clickHereLink.click();
        waitForModalToAppear();
    }

    // Dodajemy metodę ustawiającą stan w localStorage
    public void setModalClosedState() {
        page.evaluate("localStorage.setItem('modalClosed', 'true');");
    }
}
