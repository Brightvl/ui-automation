package Brightvl;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationFormPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By genderRadioButton = By.cssSelector("input[name='gender'][value='Male']");
    private By mobileField = By.id("userNumber");
    private By dateOfBirthField = By.id("dateOfBirthInput");
    private By subjectsField = By.id("subjectsInput");
    private By uploadPictureButton = By.id("uploadPicture");
    private By currentAddressField = By.id("currentAddress");
    private By stateDropdown = By.id("react-select-3-input");
    private By cityDropdown = By.id("react-select-4-input");
    private By submitButton = By.id("submit");
    private By resultPopup = By.id("example-modal-sizes-title-lg");
    private By resultTable = By.cssSelector(".table-responsive");

    public RegistrationFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void selectGender() {
        WebElement genderElement = driver.findElement(genderRadioButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", genderElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderElement);
    }

    public void enterMobile(String mobile) {
        driver.findElement(mobileField).sendKeys(mobile);
    }

    public void enterDateOfBirth(String dateOfBirth) {
        WebElement dateField = driver.findElement(dateOfBirthField);
        dateField.clear();
        dateField.sendKeys(dateOfBirth);
        dateField.sendKeys("\n");
    }

    public void enterSubjects(String subjects) {
        driver.findElement(subjectsField).sendKeys(subjects);
        driver.findElement(subjectsField).sendKeys("\n");
    }

    public void uploadPicture(String filePath) {
        driver.findElement(uploadPictureButton).sendKeys(filePath);
    }

    public void enterCurrentAddress(String address) {
        driver.findElement(currentAddressField).sendKeys(address);
    }

    public void selectState(String state) {
        driver.findElement(stateDropdown).sendKeys(state);
        driver.findElement(stateDropdown).sendKeys("\n");
    }

    public void selectCity(String city) {
        driver.findElement(cityDropdown).sendKeys(city);
        driver.findElement(cityDropdown).sendKeys("\n");
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public boolean isResultPopupDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultPopup));
        return driver.findElement(resultPopup).isDisplayed();
    }

    public String getResultTableText() {
        return driver.findElement(resultTable).getText();
    }
}
