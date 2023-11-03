package Genericutility;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Webdriverutility {

	public void maximizewindow(WebDriver driver) {
		driver.manage().window().maximize();

	}

	public void waitpagelod(WebDriver driver)

	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void select(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	public void select(WebElement element, String vtext) {
		Select sel = new Select(element);
		sel.selectByVisibleText(vtext);
	}

	public void select1(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	public void mouseover(WebDriver driver, WebElement ele) {
		Actions a = new Actions(driver);
		a.moveToElement(ele).perform();
	}

	public void movetelement(WebDriver driver, WebElement ele) {
		Actions a = new Actions(driver);
		a.moveToElement(ele).perform();
	}

	public void draganddrop(WebDriver driver, WebElement src, WebElement dst) {
		Actions a = new Actions(driver);
		a.dragAndDrop(src, dst).perform();

	}

	public void doubleclick(WebDriver driver) {
		Actions a = new Actions(driver);
		a.doubleClick().perform();
	}

	public void rightclcik(WebDriver driver, WebElement element) {
		Actions a = new Actions(driver);
		a.contextClick(element).perform();
	}

	public void enterkeypress(WebDriver driver) {
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).perform();
	}

	public void enterkey() throws Throwable {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
	}

	public void enterrelease() throws Throwable {
		Robot r = new Robot();
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	public void switchtoframes(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchtoframes(WebDriver driver, String nameOrID) {
		driver.switchTo().frame(nameOrID);
	}

	public void switchtoframes(WebDriver driver, WebElement address) {
		driver.switchTo().frame(address);

	}

	public void acceptalert(WebDriver driver) {
		driver.switchTo().alert();
	}

	public void cancelalert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void switchtowindow(WebDriver driver, String partialtitle) {
		Set<String> win = driver.getWindowHandles();
		java.util.Iterator<String> it = win.iterator();
		while (it.hasNext()) {
			String windit = it.next();
			String title = driver.switchTo().window(windit).getTitle();
			if (title.contains(partialtitle)) {
				break;
			}
		}
	}

	public void scrollbar(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,800)");

	}

	public void scrollaction(WebDriver driver, WebElement ele1) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		int y = ele1.getLocation().getY();
		jse.executeScript("window.scrollBy(0," + y + ")", ele1);
	}

	public void quits(WebDriver driver) {
		driver.quit();
	}

	public void closeApp(WebDriver driver) {
		driver.close();
	}

	public void copyandpaste(WebElement element, WebElement element1) throws Throwable {
		Robot robot = new Robot();
		element.sendKeys(Keys.CONTROL + "c");
		element1.sendKeys(Keys.CONTROL + "v");

	}

	public static String getScreenShot(WebDriver driver, String screenshotname) throws Throwable {

		Random jus = new Random();
		int j = jus.nextInt();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = "./test-output/Screenshot/" + screenshotname + j + ".png";
		File dst = new File(path);
		FileUtils.copyFile(src, dst);
		return path;

	}

	public ChromeOptions notification() {
		ChromeOptions cp = new ChromeOptions();
		cp.addArguments("--disable-notifications");
		return cp;
	}

	public void scrollup(WebDriver driver) {
		for (int i = 0; i <= 3; i++) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,-500)");
		}
	}

	public void scrolltoparticularelement(WebElement element, WebDriver driver) {
		org.openqa.selenium.Point loc = element.getLocation();
		int x = loc.getX();
		int y = loc.getY();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(" + x + "," + y + ")");

	}

	public void elementtobeclikable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void clickon(WebDriver driver, WebElement element, int x, int y) {
		Actions a = new Actions(driver);
		a.moveToElement(element);
		a.moveByOffset(x, y);
		a.click().build().perform();

	}

	public void javascroll(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
