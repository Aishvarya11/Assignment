package Helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationPracticePage {

	@FindBy(xpath="//*[@class='login']")
	public static WebElement signIn;

	@FindBy(xpath="//*[@name='email_create']")
	public static WebElement enterEmail;

	@FindBy(xpath="//*[@id='SubmitCreate']")
	public static WebElement submitCreate;

	@FindBy(xpath="//*[@id='customer_firstname']")
	public static WebElement customerFirstName;

	@FindBy(xpath="//*[@id='customer_lastname']")
	public static WebElement customer_lastName;

	@FindBy(xpath="//*[@id='passwd']")
	public static WebElement password;

	@FindBy(xpath="//*[@id='firstname']")
	public static WebElement firstname;

	@FindBy(xpath="//*[@id='lastname']")
	public static WebElement lastname;

	@FindBy(xpath="//*[@id='address1']")
	public static WebElement addressLine1;

	@FindBy(xpath="//*[@id='city']")
	public static WebElement city;

	@FindBy(xpath="//*[@id='id_state']")
	public static WebElement state;

	@FindBy(xpath="//*[@id='postcode']")
	public static WebElement zipCode;

	@FindBy(xpath="//*[@id='phone_mobile']")
	public static WebElement mobile;

	@FindBy(xpath="//*[@id='alias']")
	public static WebElement alias;

	@FindBy(xpath="//*[@id='submitAccount']")
	public static WebElement submitAccountBtn;

	@FindBy(xpath="(//*[contains(text(),'Women')])[1]")
	public static WebElement selectWomen;

	@FindBy(xpath="(//*[@class='quick-view'])[1]")
	public static WebElement quickView;

	@FindBy(xpath="//*[@class='login']")
	public static WebElement login;

	@FindBy(xpath="//*[@id='email']")
	public static WebElement email;

	@FindBy(xpath="//*[@id='passwd']")
	public static WebElement passwordLogin;

	@FindBy(xpath="//*[@id='SubmitLogin']")
	public static WebElement SubmitLogin;

	@FindBy(xpath="//*[@class='fancybox-iframe']")
	public static WebElement frame;

	@FindBy(xpath="//*[@id='quantity_wanted']")
	public static WebElement quantity;

	@FindBy(xpath="//*[@id='center_column']/ul/li[1]/div/div[1]/div/a[1]/img")
	public static WebElement mouseHover;

	@FindBy(xpath="//*[@id='add_to_cart']/button")
	public static WebElement addToCart;

	@FindBy(xpath="//*[@class='button btn btn-default standard-checkout button-medium']")
	public static WebElement checkout;

	@FindBy(xpath="//*[@class='button btn btn-default standard-checkout button-medium']")
	public static WebElement checkout2;

	@FindBy(xpath="//*[@name='processAddress']")
	public static WebElement processAddress;

	@FindBy(xpath="//*[@name='processCarrier']")
	public static WebElement processCarrier;

	@FindBy(xpath="//*[@id='cgv']")
	public static WebElement terms;

	@FindBy(xpath="//*[@class='cross']")
	public static WebElement close;

	@FindBy(xpath="//*[@id='header']/div[3]/div/div/div[3]/div/a")
	public static WebElement cart;

	@FindBy(xpath="(//*[@class='payment_module'])[2]")
	public static WebElement payment;

	@FindBy(xpath="//*[@id='cart_navigation']/button")
	public static WebElement confirmOrder;

	@FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[1]/a")
	public static WebElement profile;

	@FindBy(xpath="//*[contains(text(), 'Order history and details')]")
	public static WebElement orderHistory;

	@FindBy(xpath="(//*[@class='history_price'])[1]")
	public static WebElement orderPrice;

	@FindBy(xpath="//*[@id='total_price_container']")
	public static WebElement cartTotal;

	WebDriver driver;

	public AutomationPracticePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private AutomationPracticePage initialize() {
		return PageFactory.initElements(driver, AutomationPracticePage.class);
	}

	/*To create new user account ,first enter valid user details in properties file*/
	@SuppressWarnings("deprecation")
	public void createAccount(WebDriver driver) throws IOException {
		Properties prop=new Properties();
		FileInputStream file= new FileInputStream("E:/Assignment/src/test/resources/automationPracticeXml/config.properties");
		prop.load(file);
		String emailId= prop.getProperty("emailId");
		String loginPassword = decodeString();
		String userFirstname= prop.getProperty("userFirstname");
		String userLastname = prop.getProperty("userLastname");
		
		signIn.click();
		enterEmail.sendKeys(emailId);
		submitCreate.click();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(customerFirstName));
		customerFirstName.sendKeys(userFirstname);
		customer_lastName.sendKeys(userLastname);
		password.sendKeys(loginPassword);
		firstname.sendKeys(userFirstname);
		lastname.sendKeys(userLastname);
		addressLine1.sendKeys("3275 NW 24th Street Rd");
		city.sendKeys("Miami");
		Select sel=new Select(state);
		sel.selectByVisibleText("Florida");
		zipCode.sendKeys("33142");
		mobile.sendKeys("9876543210");
		alias.sendKeys("MyAddress");
		submitAccountBtn.click();
	}

	/*if already registered, use this to login*/
	@SuppressWarnings("deprecation")
	public void signIn(WebDriver driver) throws IOException{
		Properties prop=new Properties();
		FileInputStream file= new FileInputStream("E:/Assignment/src/test/resources/automationPracticeXml/config.properties");
		prop.load(file);
		String emailId= prop.getProperty("emailId");
		String loginPassword = decodeString();
		login.click();
		email.sendKeys(emailId);
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(passwordLogin));
		passwordLogin.sendKeys(loginPassword);
		SubmitLogin.click();
	}

	@SuppressWarnings("deprecation")
	public void selectOrder(WebDriver driver){
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(selectWomen));
		selectWomen.click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		Actions action = new Actions(driver);
		action.moveToElement(mouseHover).perform();
		quickView.click();
		driver.switchTo().frame(frame); 
		quantity.clear();
		quantity.sendKeys("2");
		addToCart.click();
	}

	public String checkout(WebDriver driver){
		driver.navigate().refresh();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-1000)");
		cart.click();
		js.executeScript("window.scrollBy(0,700)");
		String total= cartTotal.getText();
		checkout.click();
		js.executeScript("window.scrollBy(0,1000)");
		processAddress.click();
		js.executeScript("window.scrollBy(0,1000)");
		terms.click();
		processCarrier.click();
		js.executeScript("window.scrollBy(0,1000)");
		payment.click();
		confirmOrder.click();
		return total;
	}

	public String orderHistory(WebDriver driver){
		profile.click();
		orderHistory.click();
		String price= orderPrice.getText();
		return price;
	}

	protected static String decodeString() throws IOException{	
		Properties prop=new Properties();	
		FileInputStream file= new FileInputStream("E:/Assignment/src/test/resources/automationPracticeXml/config.properties");
		prop.load(file);
		String userPassword= prop.getProperty("userPassword");
		byte[] actualByte = Base64.getDecoder().decode(userPassword); 
		String loginPassword = new String(actualByte);
		return loginPassword;
	}
}
