package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;
import java.util.Random;


public class MotorWayPage extends Driver {


    public WebElement txtRegField(){
        return driver.findElement(By.id("vrm-input"));
    }

    public WebElement txtMileageField(){
        return driver.findElement(By.id("mileage-input"));
    }

    public WebElement btnConfirmMileage(){
        return driver.findElement(By.cssSelector("[data-cy='continueButton']"));
    }

    public WebElement txtEmailField(){
        return driver.findElement(By.cssSelector("[type='email']"));
    }

    public WebElement btnContinue(){
        return driver.findElement(By.cssSelector("[type='submit']"));
    }

    public WebElement txtFullNameField(){
        return driver.findElement(By.cssSelector("[data-cy='nameInput']"));
    }

    public WebElement txtMobileNumberField(){
        return driver.findElement(By.cssSelector("[name='phone']"));
    }

    public WebElement btnValueMyCar(){
        return driver.findElement(By.xpath("//*[@id='main']/div[1]/div[3]/div/div[2]/div/div/div/section/form/button/span[1]"));
    }

    public WebElement btnSeeMyValuation(){
        return driver.findElement(By.cssSelector("[data-cy='seeValuationButton']"));
    }

    public WebElement btnAcceptCookies(){
        return driver.findElement(By.id("onetrust-accept-btn-handler"));
    }

    public void EnterRegistration(String regNumber)
    {
            txtRegField().sendKeys(regNumber);
    }

    public void GetCarValuation()
    {
       btnValueMyCar().click();
    }

    public void ConfirmMileage()
    {
        btnConfirmMileage().click();
    }

    public void AcceptAllCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        try {
            Thread.sleep(10000);
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(btnAcceptCookies()));
            if (acceptButton.isDisplayed()) {
                acceptButton.click();
            }
        } catch (Exception e) {
            System.out.println("not found: " + e.getMessage());
        }
    }

    public String generateRandomMileage() {
        Random random = new Random();
        int minMileage = 1000;
        int maxMileage = 100000;
        int regMileage = random.nextInt((maxMileage - minMileage) + 1) + minMileage;

        return String.valueOf(regMileage);
    }

    public String getCarValuationResult() throws InterruptedException {
        Thread.sleep(5000);
        return driver.findElement(By.cssSelector("[data-cy='estimatedSalePrice']")).getText();
    }

    public void EnterEmailAddress(String emailAddress) {
        try{
            Thread.sleep(10000);
            txtEmailField().sendKeys(emailAddress);
        }
        catch(Exception e){
            System.out.println("not found: " + e.getMessage());
        }
    }

    public void ClickContinueButton(){
        btnContinue().click();
    }

    public void EnterMobileNumber(String mobileNumber) {
        try{
            Thread.sleep(5000);
            txtMobileNumberField().sendKeys(mobileNumber);
        }
        catch (Exception e){
            System.out.println("not found: " + e.getMessage());
        }
    }

    public void EnterFullName(String fullName) {
        try{
            Thread.sleep(5000);
            txtFullNameField().sendKeys(fullName);
        }
        catch (Exception e){
            System.out.println("not found: " + e.getMessage());
        }
    }

    public void ClickSeeValuation (){
        btnSeeMyValuation().click();
    }
}
