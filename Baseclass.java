package Genericutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Listeners(Genericutility.ExtentListeners.class)
public class Baseclass {

	Webdriverutility wu = new Webdriverutility();
	Fileutility fu = new Fileutility();
	public static WebDriver driver;

	// @Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void configurBC() throws Throwable {

		String BROWSER = fu.readdataproperty("browser");
		String URL = fu.readdataproperty("url");

		if (BROWSER.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
          options.addArguments("--remote-allow-origins=*");
		 
		  WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			// WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("invalid broswer");
		}

		driver.get(URL);
		wu.maximizewindow(driver);
		wu.waitpagelod(driver);

	}

//	@AfterSuite(alwaysRun = true)
	public void email() throws Throwable {
		Properties properties = new Properties();
		final String username = "sridhar@explorex.co";
		final String password = "wxui pupd nrjv dhfe";
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// // Create a session with your email credentials
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a MimeMessage object
			final MimeMessage emailMessage = new MimeMessage(session);
			emailMessage.setFrom(new InternetAddress(username));
			emailMessage.setRecipients(RecipientType.TO,
					InternetAddress.parse("sridharkg1990@gmail.com"));
			emailMessage.setSubject("Bridge_Automation_Reports");
			emailMessage.setText("Please find attached is the reports");
			MimeBodyPart attachmentPart = new MimeBodyPart();
			FileDataSource source = new FileDataSource(
					"C:\\Users\\prita\\Desktop\\Bridge_windows\\ExtentReporter\\" +
							ExtentListeners.fileName);

			attachmentPart.setDataHandler(new DataHandler(source));

			attachmentPart.setFileName(source.getName());

			// Create a multipart message
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(attachmentPart);

			emailMessage.setContent(multipart);
			// Send the email
			Transport.send(emailMessage);

			System.out.println("Email sent successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
