package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.UploadPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UploadTest extends BaseTest {

    @Test
    @DisplayName("Powinno się udać przesłać plik i wyświetlić jego nazwę")
    public void testFileUpload() {
        UploadPage pageObject = new UploadPage(page);
        pageObject.open();

        String fileName = "testfile.txt";
        pageObject.uploadFile(fileName);

        assertEquals(fileName, pageObject.getUploadedFileName(),
                "Nazwa przesłanego pliku powinna być widoczna na stronie.");
    }
}
