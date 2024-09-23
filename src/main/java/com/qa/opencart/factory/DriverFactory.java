package com.qa.opencart.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    public WebDriver driver;
    public Properties prop;
    public static String highlight;
    public OptionsManager optionsManager;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    /**
     * This method is use to initialize driver on basis of browser
     *
     * @param prop browser
     * @return This method will return driver
     */

    public WebDriver initDriver(Properties prop) {
        optionsManager = new OptionsManager(prop);
        highlight = prop.getProperty("highlight");
        String browser = prop.getProperty("browser").trim();
        System.out.println("The browser name is :" + browser);

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            //driver = new ChromeDriver(optionsManager.getChromeOptions());
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            // driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
            tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
        } else {
            System.out.println("Please pass the correct browser name" + browser);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().get(prop.getProperty("url"));

        return getDriver();
    }

    public synchronized WebDriver getDriver()
    {
        return tlDriver.get();
    }


    /**
     * This method is used to initialize the properties on the basis of given environment
     *
     * @return this method return prop
     */
    public Properties initProperties() {
        prop = new Properties();
        FileInputStream ip = null;

        String env = System.getProperty("env");
        if (env == null) {
            try {
                ip = new FileInputStream("./src/test/resources/config/config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Running on Environment :" + env);
            try {
                switch (env.toLowerCase()) {
                    case "qa":
                        ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;
                    case "stage":
                        ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
                        break;
                    case "dev":
                        ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;
                    default:
                        System.out.println("Please pass the right env" + env);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }


    /***
     * take screenshot
     * @return
     */

    public String getScreenshot(){
        File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}