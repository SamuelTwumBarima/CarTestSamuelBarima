package pages;

import org.openqa.selenium.WebElement;
import utils.Driver;

public class CommonPage extends Driver {

    public static void getUrl(final String url){
        driver.navigate().to(url);
    }

    public void SearchTextField(String txtToSearch, WebElement txtFieldToSearch, WebElement btnClickToSeach){
        txtFieldToSearch.sendKeys(txtToSearch);
        btnClickToSeach.click();
    }
}
