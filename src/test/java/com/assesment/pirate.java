package com.assesment;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class pirate {
	
	
//	I've deleted the Pirate.class file as it couldn't be included in this ZIP file.
//	If you need to execute this program, please let me know, and I'll email you the file.
	
	private WebDriver driver;

    @BeforeMethod
	public void setup() {
        driver = new ChromeDriver();
        driver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_csharp_search/862b0faa506b8487c25a3384cfde8af4/static/attachments/reference_page.html");
    }

    @AfterMethod
	public void teardown() {
        driver.quit();
    }

    @Test
    public void test_check_query_input_and_search_button_existence() {
        WebElement searchInput = driver.findElement(By.id("search-input"));
        WebElement searchButton = driver.findElement(By.id("search-button"));
        assertTrue(searchInput != null);
        assertTrue(searchButton != null);
    }

    @Test
    public void test_check_search_with_empty_query() {
        driver.findElement(By.id("search-button")).click();
        String errorMessage = driver.findElement(By.id("error-empty-query")).getText();
        assertEquals("Provide some query", errorMessage);
    }

    @Test	
    public void test_check_query_for_isla_returns_at_least_one_result() {
        driver.findElement(By.id("search-input")).sendKeys("isla");
        driver.findElement(By.id("search-button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#search-results li")));
        assertTrue(results.size() >= 1);
    }

    @Test
    public void test_check_feedback_for_no_results() {
        driver.findElement(By.id("search-input")).sendKeys("castle");
        driver.findElement(By.id("search-button")).click();
        String noResultsMessage = driver.findElement(By.id("error-no-results")).getText();
        assertEquals("No results", noResultsMessage);
    }

    @Test
    public void test_check_results_match_query() {
        driver.findElement(By.id("search-input")).sendKeys("port");
        driver.findElement(By.id("search-button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#search-results li")));
        assertEquals(1, results.size());
        assertEquals("Port Royal", results.get(0).getText());
    }

}
