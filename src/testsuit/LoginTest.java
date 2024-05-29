package testsuit;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials(){
        //fine username and enter name tomsmith
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        //find password filed and enter password SuperSecretPassword!
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        //find login button and click
        WebElement loginbutton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginbutton.click();
        String expectedMessage = "Secure Area";
        String actualMessage = driver.findElement(By.tagName("h2")).getText();
        Assert.assertEquals(expectedMessage,actualMessage);
    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        //find username field and user enter invalid username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith1");
        //find password field and user enter valid password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        //find login button and click on login button
        WebElement loginbutton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginbutton.click();
        //verify the error message"Your username is invalid!"
        String expectedMessage = "Your username is invalid!";
        boolean actualMessage = driver.findElement(By.cssSelector("div#flash")).getText().contains(expectedMessage);
        Assert.assertTrue("message does not match.",actualMessage);
    }
    @Test
    public void verifyThePasswordErrorMessage(){
        //find username field and user enter valid username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");
        //find password field and user enter invalid password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword");
        //find login button and click on login button
        WebElement loginbutton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginbutton.click();
        //Verify the error message"Your password is invalid!"
        String expectedMessage = "Your password is invalid!";
        boolean actualMessage = driver.findElement(By.cssSelector("div#flash")).getText().contains(expectedMessage);
        Assert.assertTrue("Message does not match.",actualMessage);
    }
    @After
    //close the browser
    public void tearDown(){
        //closeBrowser();
    }
}
