package automationFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FireTestCaseV3 {
    static WebDriver driver;
    static Wait<WebDriver> wait;

    public static void main(String[] args) {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 600);
        driver.get("http://www.csgofast.com/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[2]/div[2]/ul/li[3]")));
        
        System.out.println("waitover");
        int blackCount = 1, redCount = 1;
        int inaRow = 3;
        //if we win we want to reset variable
        boolean win = true;
        //if redwins we want to switch sides
        boolean redWin = true;
        
        while(blackCount < 11 && redCount < 11){
        	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div[1]/div/div/div/div/div/div[6]/div/div[1]/div[1]/div[1]/div/div[2]/div[1][@data-left-seconds='30']")));
        	
        	
        	//if it is red we reset count and set win to true to start at 10 later
        	//if we win we reset value also
        	if(driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item red']")).size() != 0 && blackCount > inaRow){
        		win = true;
        		//clear value
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[2]")).click();
        	}
        	else if(driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item black']")).size() != 0 && redCount > inaRow){
        		win = true;
        		//clear value
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[2]")).click();
        	}
        	//If black or green count goes up
        	if( (driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item black']")).size() != 0/* ||
        			driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item green']")).size() != 0*/) ){
        		blackCount++;
        		redCount = 1;
        	}
        	//if red or green count goes up
        	else if( (driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item red']")).size() != 0 /*||
        			driver.findElements(By.xpath("//li[10][@class='game-roulette-history-item green']")).size() != 0*/) ){
        		redCount++;
        		blackCount = 1;
        	}
        	//if we won before and count is greater than inaRow we start with 10
        	if( blackCount > inaRow && win == true){
        		//+10
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[3]")).click();
        		//red
        		driver.findElement(By.xpath("//div[2]/div[2]/ul/li")).click();
        		win = false;
        	}
        	else if(redCount > inaRow && win == true){
        		//+10
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[3]")).click();
        		//black
        		driver.findElement(By.xpath("//div[2]/div[2]/ul/li[3]")).click();
        		win = false;
        	}
        	//else black was inaRow+1 in a row or more but we still haven't won we double
        	if( blackCount > inaRow+1 && win == false){
        		//x2
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[7]")).click();
        		//red
        		driver.findElement(By.xpath("//div[2]/div[2]/ul/li")).click();
        	}
        	else if(redCount > inaRow && win == false){
        		//x2
        		driver.findElement(By.xpath("//div[2]/div/div[2]/ul/li[7]")).click();
        		//black
        		driver.findElement(By.xpath("//div[2]/div[2]/ul/li[3]")).click();
        	}
        	
        	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div[1]/div/div/div/div/div/div[6]/div/div[1]/div[1]/div[1]/div/div[2]/div[1][@data-left-seconds='25']")));
        	System.out.println("blackCount:" + (blackCount-1));
        	System.out.println("redCount" + (redCount-1));
        	System.out.println(win);
        	
        }
        /*1 +10
        2 red
        3 x2
        4 trash*/
        
		//Closing browser
		driver.close();
    }
}