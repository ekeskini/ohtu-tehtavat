package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        clickLink("login"); 
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("incorrect username {string} and password {string} are given")
    public void incorrectUsernameIsGiven(String username, String password) {
    	logInWith(username, password);
    }
    
    @Given("command new user is selected")
    public void commandNewUserIsSelected() {
    	driver.get(baseUrl);
        clickLink("register new user");
    }
    
    @When("a valid username {string} and valid password {string} and matching password confirmation are entered")
    public void aValidUsernameAndValidPasswordAndMatchingPasswordConfirmationAreEntered(String username, String password) {
        createUserWith(username, password, password);
    }
    
    @Then("a new user is created")
    public void aNewUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("an invalid username {string} and valid password {string} and matching password confirmation are entered")
    public void anInvalidUsernameAndValidPasswordAndMatchingPasswordConfirmationAreEntered(String username, String password) {
        createUserWith(username, password, password);
    }
    
    @Then("user is not created and error {string} is reported")
    public void userIsNotCreatedAndErrorIsReported(String string) {
    	pageHasContent(string);
    	
    }
    
    @When("a valid username {string} and invalid password {string} and matching password confirmation are entered")
    public void aValidUsernameAndInvalidPasswordAndMatchingPasswordConfirmationAreEntered(String username, String password) {
        createUserWith(username, password, password);
    }
    
    @When("a valid username {string} and valid password {string} and not matching password confirmation {string} are entered")
    public void aValidUsernameAndValidPasswordAndNotMatchingPasswordConfirmationAreEntered(String username, String password1, String password2) {
        createUserWith(username, password1, password2);
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void userWithUsernameWithPasswordIsSuccessfullyCreated(String username, String password) {
        driver.get(baseUrl);
    	clickLink("register new user");
    	createUserWith(username, password, password);
        pageHasContent("Welcome to Ohtu Application!");
        clickLink("continue to application mainpage");
        pageHasContent("Ohtu Application main page");
        clickLink("logout");
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userWithUsernameAndPasswordIsTriedToBeCreated(String username, String password) {
        driver.get(baseUrl);
        clickLink("register new user");
        createUserWith(username, password, password);
        pageHasContent("username should have at least 3 characters");
        clickLink("back to home");
    }

    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
       
        	assertTrue(driver.getPageSource().contains(content));
        
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    
    private void createUserWith(String username, String password, String password2) {
    	assertTrue(driver.getPageSource().contains("Create username and give password"));
    	Random r = new Random();
    	WebElement element = driver.findElement(By.name("username"));
    	element.sendKeys(username);
    	element = driver.findElement(By.name("password"));
    	element.sendKeys(password);
    	element = driver.findElement(By.name("passwordConfirmation"));
    	element.sendKeys(password2);
    	element.submit();
    }
    
    private void clickLink(String linktext) {
    	WebElement element = driver.findElement(By.linkText(linktext));
    	element.click();
    }
    
}
