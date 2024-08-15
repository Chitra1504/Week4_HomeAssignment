package week4.homework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionSnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//Home Assignment <2> : Automation using Actions Class in Selenium
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.snapdeal.com/");	
		//driver.findElement(By.xpath("//button[@id='pushDenied']")).click();
		//Thread.sleep(1000);
		WebElement men=driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]/parent::a"));
		Actions opt=new Actions(driver);
		opt.moveToElement(men).perform();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']/parent::a")).click();
		System.out.println("The count of sport shoes is "+driver.findElement(By.xpath("//div[@class='child-cat-count ']")).getText());
		driver.findElement(By.xpath("//div[text()='Training Shoes']/parent::a")).click();
		driver.findElement(By.xpath("//div[@class='sort-drop clearfix']/i")).click();
		//Thread.sleep(500);
		driver.findElement(By.xpath("(//li[@class='search-li'])[1]")).click();
		Thread.sleep(3000);
		List<WebElement> shoes=driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<Integer> price = new ArrayList<Integer>();
		for(int i=0;i<shoes.size();i++) {
			String text=shoes.get(i).getAttribute("data-price");
			price.add(Integer.parseInt(text));
		}
		List<Integer> sortPrice=new ArrayList<Integer>();
		sortPrice.addAll(price);
		Collections.sort(sortPrice);
		if(sortPrice.equals(price))
			System.out.println("The displayed items are sorted correctly");
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("500");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("700");
		driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow')]")).click();
		Thread.sleep(1000);
		WebElement selectColor=driver.findElement(By.xpath("//a[text()=' White & Blue']/parent::label"));
		opt.scrollToElement(selectColor).perform();
		selectColor.click();
		Thread.sleep(1000);
		String priceRange=driver.findElement(By.xpath("//a[@data-key='Price|Price']")).getText();
		String color=driver.findElement(By.xpath("//a[@data-key='Color_s|Color']")).getText();
		if(priceRange.equals("Rs. 500 - Rs. 700") & color.equals("White & Blue"))
			System.out.println("The filters are applied correctly");
		WebElement firstShoe=driver.findElement(By.xpath("//div[contains(@class,'product-tuple-listing')]"));
		opt.moveToElement(firstShoe).perform();
		driver.findElement(By.xpath("//div[contains(@class,'center quick-view-bar')]")).click();
		System.out.println("The price of the selected shoe is "+driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText());
		System.out.println("The disocunt of the selected shoe is "+driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText());
		WebElement selectedShoe=driver.findElement(By.xpath("//div[contains(@class,'quickViewModal ')]"));
		File src=selectedShoe.getScreenshotAs(OutputType.FILE);
		File dest=new File("./SnapShots/shoe.png");
		FileUtils.copyFile(src, dest);
		driver.close();
	}

}
