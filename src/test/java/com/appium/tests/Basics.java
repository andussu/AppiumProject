package com.appium.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Basics {

    AppiumDriver<MobileElement> driver;

    @Test
    public void test1() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2_API_26");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        //specify app ->> //path/to/apk.apk
        //or if app is already installed, you need to specify appActivity and appPackage
        //this info can be found on the net, at work - ask devs || App Info.apk ->


        //set app package name
        desiredCapabilities.setCapability("appPackage", "com.android.calculator2");

        //set your application's MainActivity - LAUNCHER activity name
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        Thread.sleep(3000);

        //Calculate 2+3 is returning 5
        MobileElement digit2 = driver.findElement(By.id("com.android.calculator2:id/digit_2"));
        MobileElement plus = driver.findElement(MobileBy.AccessibilityId("plus"));
        MobileElement minus = driver.findElement(MobileBy.AccessibilityId("minus"));
        MobileElement digit3 = driver.findElement(By.id("com.android.calculator2:id/digit_3"));
        MobileElement equals = driver.findElement(MobileBy.AccessibilityId("equals"));
        MobileElement result = driver.findElement(By.id("com.android.calculator2:id/result"));

        digit2.click();
        plus.click();
        digit3.click();
        equals.click();

        //get the text of mobile element of result
        String resultText = result.getText();
        Assert.assertEquals(resultText, "5");


        //verify 4 * 5 = 20
        MobileElement digit4 = driver.findElement(By.id("com.android.calculator2:id/digit_4"));
        MobileElement multiply = driver.findElement(MobileBy.AccessibilityId("multiply"));
        MobileElement digit5 = driver.findElement(By.id("com.android.calculator2:id/digit_5"));
        MobileElement clear = driver.findElement(MobileBy.AccessibilityId("clear"));

        clear.click();
        Thread.sleep(2000);

        digit4.click();
        multiply.click();
        digit5.click();
        equals.click();
        Thread.sleep(2000);
        resultText = result.getText();
        Assert.assertEquals(resultText, "20");

        //50-35=15
        clear.click();
        Thread.sleep(1000);
        getDigit(5).click();
        getDigit(0).click();
        minus.click();
        getDigit(3).click();
        getDigit(5).click();
        equals.click();

        resultText = result.getText();
        Assert.assertEquals(resultText, "15");
        Thread.sleep(1000);

        //close the app at the end
        driver.closeApp();
    }


    @Test
    public void test2() throws InterruptedException, MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        File appDir = new File("src/test/resources/");

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2_API_26");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        File newApp = new File(appDir, "etsy.apk");
        desiredCapabilities.setCapability("app", "https://cybertek-appium.s3.amazonaws.com/etsy.apk");
        driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);


        MobileElement you = driver.findElement((MobileBy.AccessibilityId("You tab, 4 of 5")));
        you.click();
        Thread.sleep(2000);

        MobileElement settings = driver.findElement(By.xpath("//*[@text='Settings']"));
        settings.click();
        Thread.sleep(2000);

        MobileElement checkbox = driver.findElement(By.id("com.etsy.android:id/settings_checkbox"));
        checkbox.click();
        Thread.sleep(4000);

        Assert.assertFalse(driver.findElement(By.id("com.etsy.android:id/settings_checkbox")).isSelected());

        driver.closeApp();
    }


    //method that is returning MobileElement of the digit that you pass as a parameter
    public MobileElement getDigit(int digit) {
        return driver.findElement(By.id("com.android.calculator2:id/digit_" + digit));
    }
}
