package utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtil {
    public static void capture(Page page, String resultName){
        try{

            String fileName = resultName + "_" + System.currentTimeMillis() + ".png";
            Path path = Path.of("Screenshots", fileName);

            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions()
                    .setFullPage(true));

            Files.write(path,screenshot);

            //Attaching to Allure
            Allure.addAttachment(
                    "Failure Screenshot",
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to capture screenshot", e);
        }


    }
}
