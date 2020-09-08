package com.galenframework.java.sample.components;

import static java.util.Arrays.asList;

import java.util.List;

import com.galenframework.testng.GalenTestNgTestBase;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

public abstract class GalenTestBase extends GalenTestNgTestBase {

    private static final String ENV_URL = "https://iecouat.kotakcherry.com/ieco/home";  //http://testapp.galenframework.com

    @Override
    public WebDriver createDriver(Object[] args) {
        System.setProperty("webdriver.chrome.driver", "F:/Software/galen-bin-2.4.4/TestProject/driver/chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //driver.manage().window().
        if (args.length > 0) {
            if (args[0] != null && args[0] instanceof TestDevice) {
                TestDevice device = (TestDevice)args[0];
                if (device.getScreenSize() != null) {
                    driver.manage().window().setSize(device.getScreenSize());
                }
            }
        }
        return driver;
    }

    public void load(String uri) {
        getDriver().get(ENV_URL + uri);
    }

    @DataProvider(name = "devices")
    public Object [][] devices () {
        return new Object[][] {
                 {new TestDevice("mobile", new Dimension(375, 812), asList("mobile"))},
                // {new TestDevice("tablet", new Dimension(750, 800), asList("tablet"))},
                // {new TestDevice("desktop", new Dimension(1536, 864), asList("desktop"))}
        };
    }

    public static class TestDevice {
        private final String name;
        private final Dimension screenSize;
        private final List<String> tags;

        public TestDevice(String name, Dimension screenSize, List<String> tags) {
            this.name = name;
            this.screenSize = screenSize;
            this.tags = tags;
        }

        public String getName() {
            return name;
        }

        public Dimension getScreenSize() {
            return screenSize;
        }

        public List<String> getTags() {
            return tags;
        }

        @Override
        public String toString() {
            return String.format("%s %dx%d", name, screenSize.width, screenSize.height);
        }
    }
}
