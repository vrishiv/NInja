package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utility.Utility;

public class TopMenuTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {

        openBrowser(baseUrl);
    }

    public void selectMenu(String menu){
        clickOnElement( By.linkText(menu));
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully(){

        WebElement desktop = driver.findElement(By.linkText("Desktops"));
        Actions actions = new Actions(driver);
        actions.moveToElement(desktop).click().build().perform();
        selectMenu("Show AllDesktops");
        String expected = "Desktops";
        String actual = getTextFromElement(By.xpath("//h2[contains(text(),'Desktops')]"));
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully(){

        WebElement ln = driver.findElement(By.linkText("Laptops & Notebooks"));
        Actions actions = new Actions(driver);
        actions.moveToElement(ln).click().build().perform();
        selectMenu("Show AllLaptops & Notebooks");
        String expected = "Laptops & Notebooks";
        String actual = getTextFromElement(By.xpath("//h2[contains(text(),'Laptops & Notebooks')]"));
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully(){

        WebElement components = driver.findElement(By.linkText("Components"));
        Actions actions = new Actions(driver);
        actions.moveToElement(components).click().build().perform();
        selectMenu("Show AllComponents");
        String expected = "Components";
        String actual = getTextFromElement(By.xpath("//h2[contains(text(),'Components')]"));
        Assert.assertEquals(expected,actual);

    }

    @After
    public void endTest() {

        closeBrowser();
    }

}
