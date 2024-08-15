package week4.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandle {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Home Assignment : Window Handling and Interaction Automation
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("DemoSalesManager");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("crmsfa");
		driver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("Merge Contacts")).click();
		driver.findElement(By.xpath("//td/a/img")).click();
		Set<String> windows=driver.getWindowHandles();
		List<String> window=new ArrayList<String>(windows);
		driver.switchTo().window(window.get(1));
		//As first and second contacts aren't merging taking 3rd and 4th
		driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[1]/tbody/tr[1]/td[1]")).click();
		driver.switchTo().window(window.get(0));
		driver.findElement(By.xpath("(//td/a/img)[2]")).click();
		Set<String> windows2=driver.getWindowHandles();
		List<String> window2=new ArrayList<String>(windows2);
		driver.switchTo().window(window2.get(1));
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//table[@class='x-grid3-row-table'])[2]/tbody/tr[1]/td[1]")).click();
		driver.switchTo().window(window2.get(0));
		driver.findElement(By.linkText("Merge")).click();
		Alert alert=driver.switchTo().alert();
		alert.accept();
		String title=driver.getTitle();
		if(title.equals("View Contact | opentaps CRM"))
			System.out.println("The contact is merged");
		driver.close();
	}

}
