package tests;

import org.junit.jupiter.api.Test;
import pages.FramesPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FramesTest extends BaseTest {

    @Test
    public void testMiddleFrameContent() {
        FramesPage framesPage = new FramesPage(page);
        framesPage.openNestedFrames();

        String text = framesPage.getTextFromMiddleFrame();

        assertEquals("MIDDLE", text);
    }
}
