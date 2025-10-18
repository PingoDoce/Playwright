package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class UploadPage {
    private final Page page;
    private final String url = "https://the-internet.herokuapp.com/upload";
    private final Locator chooseFileInput;
    private final Locator uploadButton;
    private final Locator uploadedFilesText;

    public UploadPage(Page page) {
        this.page = page;
        this.chooseFileInput = page.locator("#file-upload");
        this.uploadButton = page.locator("#file-submit");
        this.uploadedFilesText = page.locator("#uploaded-files");
    }

    public void open() {
        page.navigate(url);
    }

    public void uploadFile(String fileName) {
        // plik musi być w src/test/resources
        try {
            Path path = Paths.get(Objects.requireNonNull(
                    getClass().getClassLoader().getResource(fileName),
                    "Nie znalazłem pliku na classpath: " + fileName
            ).toURI());
            chooseFileInput.setInputFiles(path);
            uploadButton.click();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUploadedFileName() {
        return uploadedFilesText.innerText().trim();
    }
}
