package stepDefinitions;

import org.openqa.selenium.support.ui.Select;
import runners.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LoginSteps extends BaseClass {

    LoginPage lp;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        setUp();
        driver.get("https://google.com/");
        lp = new LoginPage(driver);
    }

    @When("the user will be entering the username {string}")
    public void the_user_will_be_entering_the_username(String textarea){
        lp.enterTextbox(textarea);
    }

//    @When("the user enters username {string}")
//    public void the_user_enters_username(String username) {
//        loginPage.enterUsername(username);
//    }
//
//    @When("the user enters password {string}")
//    public void the_user_enters_password(String password) {
//        loginPage.enterPassword(password);
//    }
//
//    @When("the user clicks on the login button")
//    public void the_user_clicks_on_the_login_button() {
//        loginPage.clickLoginButton();
//    }

    @Then("the user should be redirected to the home page")
    public void the_user_should_be_redirected_to_the_home_page() {
        // Add verification for the home page redirection
//        String expectedUrl = "https://example.com/home";
        String actualUrl = driver.getCurrentUrl();
        System.out.println(actualUrl);

//        assertTrue(actualUrl.contains(expectedUrl));
        tearDown();
    }
}
