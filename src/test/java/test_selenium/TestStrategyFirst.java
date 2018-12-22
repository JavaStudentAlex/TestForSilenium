package test_selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class TestStrategyFirst {

    private enum browsers{
        GoogleChrome,
        InternetExployer
    }

    @Test
    public void testCaseFirst(){
        WebDriver driver = getDriver(browsers.GoogleChrome);

        driver.get("https://www.sumdu.edu.ua");

        findWordNetckrackerOnPage(driver);

        goToNetckrackerDepartment(driver);

        goToMoodlePage(driver);

        loginMoodlePage(driver);

        goToQaCourse(driver);

        logoutFromMoodle(driver);
    }

    @Test
    public void testCaseSecond(){
        WebDriver driver = getDriver(browsers.GoogleChrome);

        driver.get("https://www.sumdu.edu.ua");

        findWordNetckrackerOnPage(driver);

        goToNetckrackerDepartmentByBanner(driver);

        goToMoodlePage(driver);

        loginMoodlePage(driver);

        goToQaCourse(driver);

        logoutFromMoodle(driver);
    }

    private void logoutFromMoodle(WebDriver driver){
        WebElement quitLink = driver.findElement(By.xpath("//a[contains(text(),'Выход')]"));
        quitLink.click();
    }

    private void goToQaCourse(WebDriver driver){
        WebElement linkToCourse = driver.findElement(
                By.xpath("//a[@href='http://moodle.sumdu.edu.ua/course/view.php?id=10']"));

        linkToCourse.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    private void loginMoodlePage(WebDriver driver){
        driver.findElement(By.xpath("//a[@href='http://moodle.sumdu.edu.ua/login/index.php']")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        WebElement userNameImnput = driver.findElement(By.xpath("//form[@id='login']/div/div[2]/input"));
        WebElement passwdInput = driver.findElement(By.xpath("//form[@id='login']/div/div[5]/input"));
        WebElement loginBtn = driver.findElement(By.xpath("//form[@id='login']/div/div[5]/input[2]"));

        userNameImnput.sendKeys("ict-qata");
        passwdInput.sendKeys("ICTstudent@)17");
        loginBtn.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    private void findWordNetckrackerOnPage(WebDriver driver){
        WebElement searchInput = driver.findElement(By.xpath("//div[@id='search_box']/div/form/div/input"));
        WebElement searchButton = driver.findElement(By.xpath("//div[@id='search_box']/div/form/div/input[2]"));

        searchInput.sendKeys("Netckracker");
        searchButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void goToNetckrackerDepartment(WebDriver driver){
        WebElement chooseHeader = driver.findElement(By.xpath("//div/ul/li[5]/a/span"));
        WebElement chooseItem = driver.findElement(By.xpath("//li[5]/ul/li[9]/a/span"));

        Actions action = new Actions(driver);
        action.moveToElement(chooseHeader).build().perform();
        chooseItem.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        setDriverToLastWindow(driver);
    }

    private void goToNetckrackerDepartmentByBanner(WebDriver driver){
        String xpathToBanner = "//div[@id='slider_bottom']/div/div/div/ul/li[6]/a/img";
        WebElement sliderButton = driver.findElement(By.xpath("//div[2]/div/div/div[2]/div/div/p/a"));
        clickButtonTillBannerNotHere(driver,sliderButton,xpathToBanner);

        WebElement bannerButton = driver.findElement(By.xpath("//div[@id='slider_bottom']/div/div/div/ul/li[6]/a/img"));
        bannerButton.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        setDriverToLastWindow(driver);
    }

    private void goToMoodlePage(WebDriver driver){
        WebElement linkToNext = driver.findElement(By.xpath("//div[@id='middle_container']/div/p[4]/a[3]"));
        linkToNext.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    private WebDriver getDriver(browsers browser){
        switch (browser){
            case GoogleChrome: return new ChromeDriver();
            case InternetExployer: return new InternetExplorerDriver();
        }
        return null;
    }

    private void setDriverToLastWindow(WebDriver driver){
        for (String winHandle: driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    private void clickButtonTillBannerNotHere(WebDriver driver, WebElement button, String xpathToElement){
        while(driver.findElements(By.xpath(xpathToElement)).size()==0){
            button.click();
            driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        }
    }
}

