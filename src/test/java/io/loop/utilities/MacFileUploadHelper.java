package io.loop.utilities;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class MacFileUploadHelper {


    public static void uploadFileForMac(String filePath) throws AWTException {
        Robot robot = new Robot();

        //copy the file path
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        robot.delay(1000);

        // press ⌘ + Shift + G to open go to finder
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_META);

        // Paste file path (⌘ + V)
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);

        robot.delay(1000);

        // press enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.delay(1000);

        // Press Enter again to confirm file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }


    public static void uploadFileForWindows(String filePath) throws AWTException {
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().getContents(selection);

        //simulate keyboard paste and enter
        Robot robot = new Robot();
        robot.delay(1000);

        //press CTRL + V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        //press ENTER
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);

    }

    public static void uploadFileUsingAppleScript(String filePath) throws Exception {
        // Convert to macOS POSIX-style path (replace backslashes if any)
        String posixPath = filePath.replace("\\", "/");

        // Escape any quotes that might exist in the path
        String escapedPath = posixPath.replace("\"", "\\\"");

        String script =
                "tell application \"System Events\"\n"
                        + "   delay 2\n"  // Give the file picker time to appear
                        + "   keystroke \"G\" using {command down, shift down}\n"
                        + "   delay 1\n"
                        + "   keystroke \"" + escapedPath + "\"\n"
                        + "   delay 1\n"
                        + "   keystroke return\n"
                        + "   delay 1\n"
                        + "   keystroke return\n"
                        + "end tell";

        String[] command = { "osascript", "-e", script };
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
    }

    public static void uploadFileUsingAppleScript2(String filePath) throws Exception {
        // Convert file path to POSIX format (needed for AppleScript)
        String posixPath = filePath.replace("\\", "\\\\").replace("\"", "\\\"");

        String script =
                "tell application \"System Events\"\n"
                        + "   delay 1\n"
                        + "   keystroke \"G\" using {command down, shift down}\n"
                        + "   delay 1\n"
                        + "   keystroke \"" + posixPath + "\"\n"
                        + "   delay 1\n"
                        + "   keystroke return\n"
                        + "   delay 1\n"
                        + "   keystroke return\n"
                        + "end tell";

        String[] command = { "osascript", "-e", script };
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
    }
}
