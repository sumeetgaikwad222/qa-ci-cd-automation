package com.qa.base;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import com.qa.fileRead.ReadFiles;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseClass implements Constant{
	
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	@BeforeMethod
	public void setUp() throws IOException
	{
		ReadFiles rf = new ReadFiles();
		String browser = rf.readConfig(Config, "browser");
		String url = rf.readConfig(Config, "baseUrl");
		
		if (browser == null || url == null) {
			throw new IllegalStateException("browser or baseUrl is missing in config.properties");
		}
		switch (browser.trim().toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
			driver.set(new ChromeDriver(options));
			System.out.println("========== CHROME CONFIGURATION ==========");
			System.out.println("Chrome Options: " + options.asMap());
			System.out.println("CI Environment: " + System.getenv("CI"));
			System.out.println("==========================================");

			driver.set(new ChromeDriver(options));
			break;
		case  "edge":
			System.setProperty(MSEDGEKey, MSEDGEDriverPath);
			driver.set(new EdgeDriver());
			break;
		default:
			 throw new IllegalArgumentException(
                     "Unsupported browser: " + browser);
		}
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
		driver.get().get(url);
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		try {
            if (driver.get() != null) {
                driver.get().quit();
            }
        } finally {
            driver.remove();
        }
	}
}
