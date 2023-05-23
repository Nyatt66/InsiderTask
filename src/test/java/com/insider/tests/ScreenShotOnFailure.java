package com.insider.tests;

import com.insider.utilities.Driver;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

public class ScreenShotOnFailure implements TestWatcher {

    String path;

    public ScreenShotOnFailure(String path) {
        this.path = path;
    }


    public void captureScreenshot(String fileName) {
        int i = 0;
        try {
            new File(path).mkdirs();
            try ( FileOutputStream out = new FileOutputStream(path + File.separator + "screenshot-" + fileName+ "("+ i++ + ")" + ".png")) {
                out.write(((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES));
            }
        } catch (IOException | WebDriverException e) {
            System.out.println("screenshot failed:" + e.getMessage());
        }
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {

    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {

    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        captureScreenshot(path);
    }
}
