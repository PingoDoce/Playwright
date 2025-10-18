package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.KeyPressesPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class KeyPressesTest extends BaseTest {
    private KeyPressesPage keyPressesPage;

    @BeforeEach
    public void setupTest() {
        keyPressesPage = new KeyPressesPage(page);
        keyPressesPage.open();
    }

    @AfterEach
    public void cleanupTest() {
        // BaseTest handles context/page closing & screenshotting
    }

    @Test
    public void testKeyPressA() {
        keyPressesPage.typeKey("A");
        String result = keyPressesPage.getResult();
        assertTrue(result.contains("A"), "Expected result to contain 'A'");
    }

    @Test
    public void testKeyPressEscape() {
        keyPressesPage.typeKey("Escape");
        String result = keyPressesPage.getResult();
        assertTrue(result.contains("ESCAPE"), "Expected result to contain 'ESCAPE'");
    }

    @Test
    public void testKeyPressShift() {
        keyPressesPage.typeKey("Shift");
        String result = keyPressesPage.getResult();
        assertTrue(result.contains("SHIFT"), "Expected result to contain 'SHIFT'");
    }

    @Test
    public void testKeyPressAlt() {
        keyPressesPage.typeKey("Alt");
        String result = keyPressesPage.getResult();
        assertTrue(result.contains("ALT"), "Expected result to contain 'ALT'");
    }

    @Test
    public void testKeyPressTab() {
        keyPressesPage.typeKey("Tab");
        String result = keyPressesPage.getResult();
        assertTrue(result.contains("TAB"), "Expected result to contain 'TAB'");
    }

    @Test
    public void testKeyPressSpace() {
        keyPressesPage.typeKey("Space");
        String result = keyPressesPage.getResult();
        assertTrue(result.contains("SPACE"), "Expected result to contain 'SPACE'");
    }
}
