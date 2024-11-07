package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;

public class WeBuyAnyCarPage extends Driver {
    public WebElement txtRegField(){
        return driver.findElement(By.id("vehicleReg"));
    }

    public WebElement txtMileageField(){
        return driver.findElement(By.id("Mileage"));
    }

    public WebElement btnGetMyCarValuation(){
        return driver.findElement(By.id("btn-go"));
    }


    public WebElement txtEmailField(){
        return driver.findElement(By.id("EmailAddress"));
    }

    public WebElement btnGetMyValuation(){
        return driver.findElement(By.id("advance-btn"));
    }

    public WebElement txtMobileNumberField(){
        return driver.findElement(By.id("TelephoneNumber"));
    }

    public WebElement txtPostcodeField(){
        return driver.findElement(By.id("Postcode"));
    }

    public void SearchForCarValuation(String regNumber, String mileage) {
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement regField = wait.until(ExpectedConditions.visibilityOf(txtRegField()));
        regField.sendKeys(regNumber);

        WebElement mileageField = wait.until(ExpectedConditions.visibilityOf(txtMileageField()));
        mileageField.sendKeys(mileage);

        WebElement valuationButton = wait.until(ExpectedConditions.elementToBeClickable(btnGetMyCarValuation()));
        valuationButton.click();
    }

    public void AcceptAllCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement overlay = driver.findElement(By.className("vwo-overlay"));
            if (overlay.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", overlay);
            }
        } catch (Exception e) {
            System.out.println("unable to hide overlay.");
        }

        try {
            WebElement acceptCookiesButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler"))
            );

            acceptCookiesButton.click();
            System.out.println("Successfully clicked the 'Accept all cookies' button.");

        } catch (Exception e) {
            System.out.println("Failed to click the 'Accept all cookies' button: " + e.getMessage());
        }
    }

    public void fillFindYourDetails(String emailAddress, String postcode, String mobileNumber) {
        txtEmailField().sendKeys(emailAddress);
        txtPostcodeField().sendKeys(postcode);
        txtMobileNumberField().sendKeys(mobileNumber);
        btnGetMyValuation().click();

    }

    public String getCarValuationResult() {
        return driver.findElement(By.xpath("//*[@id='wbac-app-container']/div/div/valuation/section[2]/div/div[1]/div[1]/div[1]/div/div[1]/div/div")).getText();
    }
}
