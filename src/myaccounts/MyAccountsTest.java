package myaccounts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v117.fedcm.model.Account;
import org.openqa.selenium.interactions.Actions;
import utility.Utility;

public class MyAccountsTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUP() {
        openBrowser( baseUrl );
    }

    public void selectMyAccountOptions(String option) {
        clickOnElement( By.xpath( "//*[@id=\"top-links\"]/ul/li[2]/a" ) );
    }

    @Test
    public void register() {

        Actions actions = new Actions( driver );
        driver.findElement( By.xpath( "//*[@id=\"top-links\"]/ul/li[2]/a" ) ).click();
        driver.findElement( By.xpath( "//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a" )).click();
        String expected = "Register";
//        String actual = getTextFromElement( By.xpath( "//h2[contains(text(),'Register')]" ) );
//        Assert.assertEquals( expected, actual );
        driver.findElement( By.xpath( "//*[@id=\"column-right\"]/div/a[1]" ) ).click();
        clickOnElement( By.xpath( "//*[@id=\"content\"]/div/div[1]/div/a" ) );

    }
    @Test
    public void form(){
        driver.findElement( By.xpath( "//*[@id=\"top-links\"]/ul/li[2]/a" ) ).click();
        driver.findElement( By.xpath( "//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a" )).click();

        sendTextToElement( By.id( "input-firstname" ), "Hitesh");
        sendTextToElement( By.id( "input-lastname" ),"Patel" );
        sendTextToElement( By.id( "input-email" ),"hitesh123@gmail.com" );
        sendTextToElement( By.id( "input-telephone" ),"075757575");
        sendTextToElement( By.id( "input-password" ),"hp4567");
        sendTextToElement( By.id( "input-confirm" ),"hp4567");
        clickOnElement( By.xpath( "//*[@id=\"content\"]/form/div/div/input[1]" ) );
        clickOnElement( By.xpath( "//*[@id=\"content\"]/form/div/div/input[2]" ) );
    }

    private void selectMenu(String register) {
    }
}


