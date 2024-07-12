package Brightvl;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * содержит методы для взаимодействия с элементами на странице регистрации.
 */
public class RegistrationFormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameField = By.id("firstName");
    private final By lastNameField = By.id("lastName");
    private final By emailField = By.id("userEmail");
    private final By genderRadioButton = By.cssSelector("input[name='gender'][value='Male']");
    private final By mobileField = By.id("userNumber");
    private final By dateOfBirthField = By.id("dateOfBirthInput");
    private final By subjectsField = By.id("subjectsInput");
    private final By uploadPictureButton = By.id("uploadPicture");
    private final By currentAddressField = By.id("currentAddress");
    private final By stateDropdown = By.id("react-select-3-input");
    private final By cityDropdown = By.id("react-select-4-input");
    private final By submitButton = By.id("submit");
    private final By resultPopup = By.id("example-modal-sizes-title-lg");
    private final By resultTable = By.cssSelector(".table-responsive");

    public RegistrationFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Вводят текст в поле firstName
     *
     * @param firstName имя
     */
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    /**
     * Вводят текст в поле lastName
     *
     * @param lastName фамилия
     */
    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    /**
     * Вводят текст в поле email
     *
     * @param email email
     */
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    /**
     * Выбирает пол
     */
    public void selectGender() {
        WebElement genderElement = driver.findElement(genderRadioButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", genderElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", genderElement);
    }

    /**
     * Вводят текст в поле mobile
     *
     * @param mobile телефон
     */
    public void enterMobile(String mobile) {
        driver.findElement(mobileField).sendKeys(mobile);
    }

    /**
     * Вводят текст в поле dateOfBirth
     *
     * @param dateOfBirth дата рождения
     */
    public void enterDateOfBirth(String dateOfBirth) {
        WebElement dateField = driver.findElement(dateOfBirthField);
        dateField.clear();
        dateField.sendKeys(dateOfBirth);
        dateField.sendKeys("\n");
    }

    /**
     * Вводят текст в поле subjects
     *
     * @param subjects subjects
     */
    public void enterSubjects(String subjects) {
        driver.findElement(subjectsField).sendKeys(subjects);
        driver.findElement(subjectsField).sendKeys("\n");
    }

    /**
     * Вводят текст в поле filePath
     *
     * @param filePath путь к изображению
     */
    public void uploadPicture(String filePath) {
        driver.findElement(uploadPictureButton).sendKeys(filePath);
    }

    /**
     * Вводят текст в поле address
     *
     * @param address адрес
     */
    public void enterCurrentAddress(String address) {
        driver.findElement(currentAddressField).sendKeys(address);
    }

    /**
     * Вводят текст в поле state
     *
     * @param state государство
     */
    public void selectState(String state) {
        driver.findElement(stateDropdown).sendKeys(state);
        driver.findElement(stateDropdown).sendKeys("\n");
    }

    /**
     * Вводят текст в поле city
     *
     * @param city город
     */
    public void selectCity(String city) {
        driver.findElement(cityDropdown).sendKeys(city);
        driver.findElement(cityDropdown).sendKeys("\n");
    }

    /**
     * Отправляет форму.
     */
    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    /**
     * Проверяет видимость всплывающего окна с результатом
     *
     * @return true
     */
    public boolean isResultPopupDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultPopup));
        return driver.findElement(resultPopup).isDisplayed();
    }

    /**
     * Получает текст из таблицы с результатами.
     *
     * @return строка
     */
    public String getResultTableText() {
        return driver.findElement(resultTable).getText();
    }
}
