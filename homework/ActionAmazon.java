package week4.homework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActionAmazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//Home Assignment : Automation using Actions Class in Selenium
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro",Keys.ENTER);
		String price=driver.findElement(By.className("a-price-whole")).getText();
		driver.findElement(By.xpath("//span[@class='a-icon-alt']/parent::i")).click();
		String rating=driver.findElement(By.xpath("//span[@data-hook='acr-average-stars-rating-text']")).getText();
		System.out.println("The price of the first product is "+price);
		System.out.println("The customer rating of the first product is "+rating);
		driver.findElement(By.xpath("//*[contains(@class,' a-color-base a-text-normal')]")).click();
		Set<String> windows=driver.getWindowHandles();
		List<String> tabs=new ArrayList<String>(windows);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(3000);
		File src=driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./SnapShots/phone.png");
		FileUtils.copyFile(src, dest);
		//Add to cart
		WebElement cart=driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
		cart.click();
		Thread.sleep(3000);
		String finalPrice=driver.findElement(By.xpath("//div[@id='attach-added-to-cart-message']/div[1]/div[2]/div[1]")).getText();
		if(finalPrice.contains(price))
			System.out.println("The item is added to cart sucessfully");
		driver.close();
	}

}
