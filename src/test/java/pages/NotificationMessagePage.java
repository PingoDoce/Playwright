package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class NotificationMessagePage {
    private final Page page;

    private final Locator clickHereLink;
    private final Locator notification;

    public NotificationMessagePage(Page page) {
        this.page = page;
        this.clickHereLink = page.locator("text=Click here");
        this.notification = page.locator("#flash");
    }

    public void navigate() {
        page.navigate("https://the-internet.herokuapp.com/notification_message_rendered");
    }

    public void clickHere() {
        clickHereLink.click();
        notification.waitFor(); // poczekaj aż powiadomienie będzie widoczne po przeładowaniu
    }

    public String getNotificationText() {
        return notification.innerText().trim();
    }

    public boolean isNotificationVisible() {
        return notification.isVisible();
    }
}
