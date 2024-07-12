package Brightvl;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * класс содержит тестовые методы для проверки функционала формы регистрации.
 */
public class RegistrationFormTest {

    private WebDriver driver;
    private RegistrationFormPage registrationFormPage;

    /**
     * Настройка теста, инициализация WebDriver и открытие страницы для тестирования.
     */
    @BeforeClass
    public void setUp() {
        Path driverPath = Paths.get("drivers", "chromedriver.exe").toAbsolutePath();
        if (!Files.exists(driverPath)) {
            throw new RuntimeException("Chromedriver не найден по пути: " + driverPath);
        }
        System.setProperty("webdriver.chrome.driver", driverPath.toString());

        driver = new ChromeDriver();
        registrationFormPage = new RegistrationFormPage(driver);
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form");
    }

    /**
     * метод, который вызывает методы для заполнения формы, отправки её и проверки результата.
     */
    @Test
    @Description("Заполнение формы регистрации")
    public void testRegistrationForm() {
        enterRegistrationDetails();
        registrationFormPage.submitForm();
        verifySubmissionResult();
    }

    /**
     * Заполняет данные для регистрации, включая проверку наличия изображения перед его загрузкой.
     */
    @Step("Заполнение деталей регистрации")
    private void enterRegistrationDetails() {
        registrationFormPage.enterFirstName("Ivan");
        registrationFormPage.enterLastName("Ivanov");
        registrationFormPage.enterEmail("ivanov@example.com");
        registrationFormPage.selectGender();
        registrationFormPage.enterMobile("1234567890");
        registrationFormPage.enterDateOfBirth("12 Dec 1990");
        registrationFormPage.enterSubjects("Math");

        // Проверка существования файла
        Path picturePath = Paths.get("src", "main", "resources", "img.png").toAbsolutePath();
        if (!Files.exists(picturePath)) {
            throw new RuntimeException("Файл не найден: " + picturePath.toString());
        }
        registrationFormPage.uploadPicture(picturePath.toString());

        registrationFormPage.enterCurrentAddress("Some Address");
        registrationFormPage.selectState("NCR");
        registrationFormPage.selectCity("Delhi");
    }

    /**
     *  Проверяет, что после отправки формы появляется всплывающее окно с результатами и проверяет содержимое таблицы результатов.
     */
    @Step("Проверка результатов регистрации")
    private void verifySubmissionResult() {
        Assert.assertTrue(registrationFormPage.isResultPopupDisplayed(), "Popup is not displayed");
        String resultText = registrationFormPage.getResultTableText();
        Assert.assertTrue(resultText.contains("Ivan"), "First name not found in result");
        Assert.assertTrue(resultText.contains("Ivanov"), "Last name not found in result");
        Assert.assertTrue(resultText.contains("ivanov@example.com"), "Email not found in result");
        // Add further assertions as needed
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
