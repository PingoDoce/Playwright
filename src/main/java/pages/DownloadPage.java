package pages;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class DownloadPage {
    private final Page page;
    private final Locator fileLinks;

    public DownloadPage(Page page) {
        this.page = page;
        this.fileLinks = page.locator(".example a");
    }

    public void open() {
        page.navigate("https://the-internet.herokuapp.com/download");
    }

    public List<String> getFileNames() {
        return fileLinks.allInnerTexts().stream()
                .map(String::trim)
                .toList();
    }

    public Download downloadFirstFile() {
        return page.waitForDownload(() -> fileLinks.first().click());
    }

    public int getNumberOfFiles() {
        return fileLinks.count();
    }
}
