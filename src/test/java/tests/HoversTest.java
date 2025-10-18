package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HoversPage;
import utils.BaseTest;

public class HoversTest extends BaseTest {

    @Test
    public void testHoverDisplaysCaption() {
        page.navigate("https://the-internet.herokuapp.com/hovers");
        HoversPage hoversPage = new HoversPage(page);

        int userIndex = 1; // Drugi element (0-based index)
        hoversPage.hoverOverFigure(userIndex);

        String captionText = hoversPage.getCaptionText(userIndex);
        Assertions.assertTrue(captionText.contains("user" + (userIndex + 1)), "Caption should contain correct username");
    }
}
