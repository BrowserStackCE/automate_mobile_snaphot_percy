package com.browserstack;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.RotatingDecorator;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;
import ru.yandex.qatools.ashot.shooting.ViewportPastingDecorator;
import ru.yandex.qatools.ashot.shooting.cutter.CutStrategy;
import ru.yandex.qatools.ashot.shooting.cutter.VariableCutStrategy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class AutomatePercySnapshot extends BrowserStackTestNGTest {

    @Test
    public void test() throws Exception {


    	  // navigate to bstackdemo
        driver.get("https://www.browserstack.com");
        driver.findElement(By.xpath("//button[@id='accept-cookie-notification']")).click();

        JavascriptExecutor j = (JavascriptExecutor) driver;
        Double pixelRatio = (Double) j.executeScript("var ratio = 1; if (window.screen.systemXDPI !== undefined && window.screen.logicalXDPI !== undefined && window.screen.systemXDPI > window.screen.logicalXDPI) {ratio = window.screen.systemXDPI / window.screen.logicalXDPI;} else if (window.devicePixelRatio !== undefined) {ratio = window.devicePixelRatio;} return ratio;");

        Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportRetina(1000, 0, 0, pixelRatio.floatValue())).takeScreenshot(driver);
        try {
            ImageIO.write(screenshot.getImage(),"PNG",new File("screenshots/homepage-"+capabilities.getCapability("device")+".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static JSONObject getSessionDetails( WebDriver driver) {
        final JavascriptExecutor jse = (JavascriptExecutor) driver;
        JSONObject responseJson = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");;
        Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
        try {
            responseJson = new JSONObject(response.toString());
        }catch (JSONException err){
            System.out.println("unable to parse the session details");
        }
        return responseJson;
    }


}
