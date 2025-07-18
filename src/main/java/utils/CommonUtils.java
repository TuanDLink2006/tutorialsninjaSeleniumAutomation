package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class CommonUtils {

    public static String generateEmail(){
        return new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "") + "@gmail.com";
    }

    public static boolean compareToScreenshot(String actualImagePath, String expectedImagePath) throws IOException {
        BufferedImage acutualBImg = null;
        BufferedImage expectedBImg = null;
        try {
            acutualBImg = ImageIO.read(new File(System.getProperty("user.dir")+actualImagePath));
            expectedBImg = ImageIO.read(new File(System.getProperty("user.dir")+expectedImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageDiffer imgDiffer = new ImageDiffer();
        ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, acutualBImg);

        return imgDifference.hasDiff();
    }

    public static void setProperties(String propertyKey, String propertyValue, Properties prop ) throws IOException {
        prop = CommonUtils.loadProperties();
        prop.setProperty(propertyKey, propertyValue);
        FileWriter fr = null;
        try {
            fr = new FileWriter(System.getProperty("user.dir") + "\\src\\test\\resources\\projectData.properties");
            prop.store(fr, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fr.close();
    }

    public static Properties loadProperties(){
        Properties prop = new Properties();

        try {
            FileReader fr = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\projectData.properties");
            prop.load(fr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return prop;
    }

    public static WebDriver takeScreenshot(WebDriver driver, String pathToCopied){

        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshot, new File(System.getProperty("user.dir") + pathToCopied));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return driver;
    }

    public static String takeScreenshotAndReturnPath(WebDriver driver, String pathToBCopied) {
        if (driver == null) {
            System.err.println("WebDriver is null, cannot take screenshot.");
            return null;
        }

        String destScreenshotPath = System.getProperty("user.dir") + pathToBCopied;
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcScreenshot, new File(destScreenshotPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destScreenshotPath;
    }


    public static ExtentReports getExtentReport(){
        ExtentReports extentReport = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir") + "\\reports\\TNExtentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        ExtentSparkReporterConfig sparkReporterConfig = sparkReporter.config();
        sparkReporterConfig.setReportName("Tutorials Ninja Test Automation Results");
        sparkReporterConfig.setDocumentTitle("TNER Results");

        extentReport.attachReporter(sparkReporter);
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReport.setSystemInfo("Username", System.getProperty("user.name"));
        extentReport.setSystemInfo("Selenium WebDriver Version", "4.24.0");

        return extentReport;
    }

}
