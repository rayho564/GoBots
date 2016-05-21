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


public class FireTestCaseV4 {
    static WebDriver driver;
    static Wait<WebDriver> wait;

    public static void main(String[] args) {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 700);
        driver.get("http://www.csgofast.com/");
        
        String presence = "//div[2]/div[2]/ul/li[3]";
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(presence)));
        
        System.out.println("waitover");
        int blackCount = 0, redCount = 0;
        int inaRow = 3;
        int max = 8;
        //if we win we want to reset variable
        boolean win = true;
        //if redwins we want to switch sides
        boolean redWin = true;
        
        String redHistory = "//li[10][@class='game-roulette-history-item red']";
        String redzero = "//li[9][@class='game-roulette-history-item red']";
        String blackHistory = "//li[10][@class='game-roulette-history-item black']";
        String blackzero = "//li[9][@class='game-roulette-history-item black']";
        String zeroHistory = "//li[10][@class='game-roulette-history-item zero']";
        String clear = "//div[2]/div/div[2]/ul/li[2]";
        String plusTen = "//div[2]/div/div[2]/ul/li[3]";
        String betRed = "//div[2]/div[2]/ul/li";
        String betBlack = "//div[2]/div[2]/ul/li[3]";
        String timesTwo = "//div[2]/div/div[2]/ul/li[7]";
        String timeThirty = "//div/div[2]/div/div[2]/div/div[6]/div/div[1]/div[1]/div[1]/div/div[2]/div[1][@data-left-seconds='30']";
        String timeTwoFive = "//div/div[2]/div/div[2]/div/div[6]/div/div[1]/div[1]/div[1]/div/div[2]/div[1][@data-left-seconds='25']";
        
        while(blackCount < 12 && redCount < 12){
        	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(timeThirty)));
        	
        	
        	//if it is red we reset count and set win to true to start at 10 later
        	//if we win we reset value also
        	if(driver.findElements(By.xpath(redHistory)).size() != 0 && blackCount >= inaRow){
        		win = true;
        		//clear value
        		driver.findElement(By.xpath(clear)).click();
        		
        		driver.navigate().refresh();
        	}
        	else if(driver.findElements(By.xpath(blackHistory)).size() != 0 && redCount >= inaRow){
        		win = true;
        		//clear value
        		driver.findElement(By.xpath(clear)).click();
        		
        		driver.navigate().refresh();
        	}
        	//If black or zero count goes up
        	if( (driver.findElements(By.xpath(blackHistory)).size() != 0 || 
        			(driver.findElements(By.xpath(zeroHistory)).size() != 0 &&
        			driver.findElements(By.xpath(blackzero)).size() != 0) ) ){
        		blackCount++;
        		redCount = 0;
        	}
        	//if red or zero count goes up
        	else if( (driver.findElements(By.xpath(redHistory)).size() != 0 || 
        			(driver.findElements(By.xpath(zeroHistory)).size() != 0 &&
        			driver.findElements(By.xpath(redzero)).size() != 0 ) ) ){
        		redCount++;
        		blackCount = 0;
        	}
        	//max in a row is too risky
        	if(blackCount < max && redCount < max)
        	{
	        	//if we won before and count is greater than inaRow we start with 10
	        	if( blackCount == inaRow && win == true){
	        		//+10
	        		driver.findElement(By.xpath(plusTen)).click();
	        		//+10
	        		//driver.findElement(By.xpath(plusTen)).click();
	        		//red
	        		driver.findElement(By.xpath(betRed)).click();
	        		win = false;
	        	}
	        	else if(redCount == inaRow && win == true){
	        		//+10
	        		driver.findElement(By.xpath(plusTen)).click();
	        		//+10
	        		//driver.findElement(By.xpath(plusTen)).click();
	        		//black
	        		driver.findElement(By.xpath(betBlack)).click();
	        		win = false;
	        	}
	        	//else black was inaRow+1 in a row or more but we still haven't won we double
	        	if( blackCount >= inaRow+1 && win == false){
	        		//x2
	        		driver.findElement(By.xpath(timesTwo)).click();
	        		//red
	        		driver.findElement(By.xpath(betRed)).click();
	        	}
	        	else if(redCount >= inaRow+1 && win == false){
	        		//x2
	        		driver.findElement(By.xpath(timesTwo)).click();
	        		//black
	        		driver.findElement(By.xpath(betBlack)).click();
	        	}
        	}
        	
        	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(timeTwoFive)));
        	System.out.println("blackCount:" + (blackCount));
        	System.out.println("redCount" + (redCount));
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