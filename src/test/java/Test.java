import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;

public class Test {

    WebDriver driver;
    NotebooksPage notebooksPage;

    @BeforeTest
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15)); //время на ввод капчи

    }

    @Parameters({"company"})
    @org.testng.annotations.Test
    public void testLoaded(String company) throws InterruptedException {
        Actions action = new Actions(driver);
        HomePage homePage = new HomePage(driver);
        action.sendKeys(Keys.ESCAPE).perform();//убрать рекламный баннер
        Assert.assertTrue(homePage.pageLoaded());
        CatalogPage catalogPage = homePage.clickCatalogButton();
        ComputersPage computersPage = catalogPage.clickComputersButton();
        Assert.assertTrue(computersPage.pageLoaded());
        notebooksPage = computersPage.clickNotebookButton();
        Assert.assertTrue(notebooksPage.pageLoaded());
        notebooksPage.setPrice();
        notebooksPage.selectCompany(company);
        Thread.sleep(5000);

    }

    @org.testng.annotations.Test(dependsOnMethods = "testLoaded")
    public void testResultPrices() throws InterruptedException {

        ArrayList<Integer> prices = notebooksPage.getResultPrices();
        if (prices.size() == 0) Assert.assertTrue(false);
        for(int price: prices) {
            Assert.assertTrue(price>=25000 && price<=30000);
        }

    }

    @Parameters({"company"})
    @org.testng.annotations.Test(dependsOnMethods = "testLoaded")
    public void testResultCompanies(String company) throws InterruptedException {

        ArrayList<String> companies = notebooksPage.getResultDescriptions();
        if (companies.size() == 0) Assert.assertTrue(false);
        for(String com: companies) {
            Assert.assertTrue(com.contains(company));
        }

    }

    @AfterTest
    public void quit() {
         driver.quit();
    }


}
