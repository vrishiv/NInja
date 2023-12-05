package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utility.Utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {

        openBrowser( baseUrl );
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {

        WebElement ln = driver.findElement( By.linkText( "Laptops & Notebooks" ) );
        Actions actions = new Actions( driver );
        actions.moveToElement( ln ).click().build().perform();
        clickOnElement( By.linkText( "Show AllLaptops & Notebooks" ) );
        selectByVisibleTextFromDropDown( By.id( "input-sort" ), "Price (High > Low)" );
        List<WebElement> price = driver.findElements( By.xpath( "//span[@class = 'price-tax']" ) );
        List<Double> prices = new ArrayList<>();
        for (WebElement element : price) {
            String priceText = element.getText().replaceAll( "[E,x,T,a,x,:,$]", "" ).replace( ",", "" );
            Double priceValue = Double.parseDouble( priceText );
            prices.add( priceValue );
        }

        Boolean isSorted = true;
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get( i ) < prices.get( i + 1 )) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            System.out.println( "Prices are in high to low order." );
        } else {
            System.out.println( "Prices are not sorted." );
        }

    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() {

        WebElement ln = driver.findElement( By.linkText( "Laptops & Notebooks" ) );
        Actions actions = new Actions( driver );
        actions.moveToElement( ln ).click().build().perform();

        clickOnElement( By.linkText( "Show AllLaptops & Notebooks" ) );

        selectByVisibleTextFromDropDown( By.id( "input-sort" ), "Price (High > Low)" );

        clickOnElement( By.linkText( "MacBook" ) );
        String eMacbook = "MacBook";
        String aMacbook = getTextFromElement( By.xpath( "//h1[contains(text(),'MacBook')]" ) );
        Assert.assertEquals( eMacbook, aMacbook );

        clickOnElement( By.id( "button-cart" ) );
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 20 ) );

        String eSuccessMsg = "Success: You have added MacBook to your shopping cart!\n" +
                "×";
        String aSuccessMsg = driver.findElement( By.xpath( "//div[@class = 'alert alert-success alert-dismissible']" ) ).getText();
        Assert.assertEquals( eSuccessMsg, aSuccessMsg );

        try {
            clickOnElement( By.xpath( "//a[contains(text(),'shopping cart')]" ) );
        } catch (ElementClickInterceptedException e) {
            clickOnElement( By.xpath( "//a[contains(text(),'shopping cart')]" ) );
        }

        String eText = "Shopping Cart  (0.00kg)";
        String aText = driver.findElement( By.xpath( "//h1[contains(text(),'Shopping Cart')]" ) ).getText();
        Assert.assertEquals( eText, aText );

        String eProductName = "MacBook";
        String aProductName = getTextFromElement( By.xpath( "//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]" ) );
        Assert.assertEquals( eProductName, aProductName );

        driver.findElement( By.xpath( "//tbody/tr[1]/td[4]/div[1]/input[1]" ) ).clear();
        sendTextToElement( By.xpath( "//tbody/tr[1]/td[4]/div[1]/input[1]" ), "2" );
        clickOnElement( By.xpath( "//tr[1]//button[@data-original-title = 'Update']" ) );

        String eSuccessMsg1 = "$1,204.00";
        String aSuccessMsg1 = driver.findElement( By.xpath( "//tbody/tr[1]/td[6]" ) ).getText();
        Assert.assertEquals( eSuccessMsg1, aSuccessMsg1 );

        clickOnElement( By.xpath( "//a[contains(text(),'Checkout')]" ) );
        String eCheckout = "Checkout";
        String aCheckout = getTextFromElement( By.xpath( "//h1[contains(text(),'Checkout')]" ) );
        Assert.assertEquals( eCheckout, aCheckout );

        String eNewcustomer = "New Customer";
        String aNewcustomer = getTextFromElement( By.xpath( "//h2[contains(text(),'New Customer')]" ) );
        Assert.assertEquals( eCheckout, aCheckout );

        clickOnElement( By.xpath( "//input[@value = 'guest']" ) );
        clickOnElement( By.id( "button-account" ) );

        sendTextToElement( By.id( "input-payment-firstname" ), "Shiv" );
        sendTextToElement( By.id( "input-payment-lastname" ), "Patel" );
        sendTextToElement( By.id( "input-payment-email" ), "SP@yahoo.com" );
        sendTextToElement( By.id( "input-payment-telephone" ), "9825198251" );
        sendTextToElement ( By.id( "input-payment-password" ), "sp98765" );
        sendTextToElement( By.id( "Password Confirm" ), "sp98765" );
        sendTextToElement( By.id( "input-payment-address-1" ), "wallmart" );
        sendTextToElement( By.id( "input-payment-city" ), "Salford" );
        sendTextToElement( By.id( "input-payment-postcode" ), "MA21ND" );
        selectByVisibleTextFromDropDown( By.id( "input-payment-zone" ), "Manchester" );
        clickOnElement( By.xpath( "//body/div[@id='checkout-checkout']/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[4]/div[1]/input[1]" ) );
        clickOnElement( By.id( "button-guest" ) );
        sendTextToElement( By.xpath( "//textarea[@class = 'form-control']" ), "Successful" );
        clickOnElement( By.xpath( "//input[@type='checkbox']" ) );
        clickOnElement( By.id( "button-payment-method" ) );

        String eWarning = "Warning: Payment method required!\n" +
                "×";
        String aWarning = driver.findElement( By.xpath( "//div[text()='Warning: Payment method required!']" ) ).getText();
        Assert.assertEquals( eWarning, aWarning );


    }

    @After
    public void tearDown() {

//        closeBrowser();
    }
}
