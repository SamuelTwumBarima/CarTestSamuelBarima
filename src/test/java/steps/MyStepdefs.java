package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import pages.CommonPage;
import pages.MotorWayPage;
import pages.WeBuyAnyCarPage;
import utils.FileReaderUtil;

import java.util.List;
import java.util.Map;


public class MyStepdefs extends CommonPage {

    private FileReaderUtil fileReaderUtil;
    private List<String> regNumbers;
    private Map<String, String> expectedValuations;

    WeBuyAnyCarPage weBuyAnyCarPage = new WeBuyAnyCarPage();

    @Given("A user opens url {string}")
    public void aUserOpensUrl(String url) {
        getUrl(url);
        Cookie countryCookie = new Cookie("wbacselectedcountry", "UK");
       driver.manage().addCookie(countryCookie);

        driver.navigate().refresh();
        closeAlertIfPresent();
       weBuyAnyCarPage.AcceptAllCookies();
    }


    @And("The user retrieves car reg numbers from {string}")
    public void theUserRetrievesCarRegNumbersFrom(String fileInput) {
        fileReaderUtil = new FileReaderUtil();
        regNumbers = fileReaderUtil.readRegNumber("src/test/resources/"+fileInput);
    }

    @When("The user retrieves valuations from {string}")
    public void theUserRetrievesValuationsFrom(String fileOutput) {
        expectedValuations = fileReaderUtil.readExpectedResults("src/test/resources/" + fileOutput);
    }

    @Then("each reg should match the expected results")
    public void eachRegShouldMatchTheExpectedResults() throws InterruptedException {
        String emailAddress ="Joeydoe21@gmail.com";
        String mobileNumber = "07799888765";
        String postcode = "UB3 4PB";
        String mileage = "95000";

        for (String regNumber : expectedValuations.keySet()) {
            weBuyAnyCarPage.SearchForCarValuation(regNumber,mileage);
            weBuyAnyCarPage.fillFindYourDetails(emailAddress, postcode, mobileNumber);

            for (Map.Entry<String, String> entry : expectedValuations.entrySet()) {
                System.out.println("Reg Number: " + entry.getKey() + " Valuation: " + entry.getValue());
            }

            String actualValuation = weBuyAnyCarPage.getCarValuationResult();
            String expectedValuation = expectedValuations.get(regNumber);

           Assert.assertEquals("Valuation not the same for registration: " + regNumber, expectedValuation, actualValuation);
    }
    }
}
