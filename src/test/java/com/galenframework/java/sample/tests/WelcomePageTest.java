package com.galenframework.java.sample.tests;

import java.io.IOException;

import com.galenframework.java.sample.components.GalenTestBase;

import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class WelcomePageTest extends GalenTestBase {


    @Test(dataProvider = "devices",enabled = true)
    public void welcomePage_shouldLookGood_onDesktop(TestDevice device) throws IOException {
        load("/");
        checkLayout("/specs/iecoWelcomePage.spec", device.getTags());
    }  


    @Test(dataProvider = "devices",enabled = false)
    public void welcomePage_shouldLookGood_onDevice(TestDevice device) throws IOException {
        load("/");
        checkLayout("/specs/welcomePage.spec", device.getTags());
    }

    @Test(dataProvider = "devices",enabled = false)
    public void loginPage_shouldLookGood_onDevice(TestDevice device) throws IOException {
        load("/");
        getDriver().findElement(By.xpath("//button[.='Login']")).click();
        checkLayout("/specs/loginPage.spec", device.getTags());
    }

}
