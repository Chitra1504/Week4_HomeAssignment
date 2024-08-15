package week4.homework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionBigBasket {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//Home Assignment <3>: Automation using Actions Class in Selenium
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.bigbasket.com/");
		driver.findElement(By.xpath("(//div[@class='relative h-full']/button)[2]")).click();
		Thread.sleep(1000);
		WebElement grains=driver.findElement(By.xpath("(//a[text()='Foodgrains, Oil & Masala'])[2]"));
		Actions op=new Actions(driver);
		op.moveToElement(grains).perform();
		WebElement rice=driver.findElement(By.linkText("Rice & Rice Products"));
		op.moveToElement(rice).perform();
		driver.findElement(By.linkText("Boiled & Steam Rice")).click();
		Thread.sleep(3000);
		WebElement bbRoyal=driver.findElement(By.id("i-BBRoyal"));
		op.scrollToElement(bbRoyal).perform();
		bbRoyal.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[text()='Tamil Ponni Boiled Rice']")).click();
		Set<String> windows=driver.getWindowHandles();
		List<String> tabs=new ArrayList<String>(windows);
		driver.switchTo().window(tabs.get(1));
		Thread.sleep(1000);
		WebElement kg=driver.findElement(By.xpath("//span[text()='5 kg']"));
		op.scrollToElement(kg).perform();
		kg.click();
		String price=driver.findElement(By.xpath("//table/tr[1]/td[1]")).getText();
		System.out.println("The 5Kg of Tamil Ponni Boiled Rice "+price);
		driver.findElement(By.xpath("//button[text()='Add to basket']")).click();
		Thread.sleep(500);
		String text=driver.findElement(By.xpath("//p[@class='mx-4 flex-1']")).getText();
		if(text.contains("added"))
			System.out.println("The item is added sucessfully");
		File src=driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./SnapShots/bigbasket.png");
		FileUtils.copyFile(src, dest);
		driver.close();
		driver.switchTo().window(tabs.get(0));
		driver.close();
	}

}
