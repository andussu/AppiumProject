package com.appium.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Part1 {

    AppiumDriver<MobileElement> driver;

    @Test
    public void test1() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "8.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2_API_26");

        //specify app ->> //path/to/apk.apk
        //or if app is already installed, you need to specify appActivity and appPackage
        //this info can be found on the net, at work - ask devs || App Info.apk ->


        //set app package name
        desiredCapabilities.setCapability("appPackage", "com.android.calculator2");

        //set your application's MainActivity - LAUNCHER activity name
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

        driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        Thread.sleep(3000);
        driver.closeApp();
    }
}
