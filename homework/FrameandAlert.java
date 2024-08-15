package week4.homework;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameandAlert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Classroom Assignment <5>: Automating Frame and Alert Interactions with Text Verification
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt");
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		Alert alert=driver.switchTo().alert();
		alert.accept();
		String text=driver.findElement(By.id("demo")).getText();
		if(text.contains("Harry Potter"))
			System.out.println("Frame and Alert actions are performed correctly");
		driver.close();
	}

}
