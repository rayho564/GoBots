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


public class FireCaseV4ForCSGOPolygon {
    static WebDriver driver;
    static Wait<WebDriver> wait;

    public static void main(String[] args) {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 1000);
        driver.get("http://www.csgopolygon.com/");
        
        String presence = "//ul[2]/li/a";
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(presence)));
        
        System.out.println("waitover");
        int blackCount = 1, redCount = 1;
        int inaRow = 2;
        //if we win we want to reset variable
        boolean win = true;
        //if redwins we want to switch sides
        boolean redWin = true;
        
        String redHistory = "//div[4]/div/div[10][@class='ball ball-1']";
        String redzero = "//div[4]/div/div[9][@class='ball ball-1']";
        String blackHistory = "//div[4]/div/div[10][@class='ball ball-8']";
        String blackzero = "//div[4]/div/div[9][@class='ball ball-8']";
        String zeroHistory = "//div[4]/div/div[10][@class='ball ball-0']";
        String clear = "//div[@id='mainpage']/div[5]/div/div/button";
        String plusTen = "//div[@id='mainpage']/div[5]/div/div/button[4]";
        
        //CSGO Double point value are higher by x10
        String plusHundred = "//div[@id='mainpage']/div[5]/div/div/button[5]";
        String betRed = "//div[@id='panel1-7']/div/button";
        String betBlack = "//div[@id='panel8-14']/div/button";
        String timesTwo = "//div[@id='mainpage']/div[5]/div/div/button[8]";
        //String timeThirty = "//div[6]/div[2][@style='Rolling in 30']";
        String timeTwoFive = "//div/div[1]/div/div/div/div/div/div[6]/div/div[1]/div[1]/div[1]/div/div[2]/div[1][@data-left-seconds='25']";
        
        while(blackCount < 11 && redCount < 11){
        	wait.until(new ExpectedCondition<Boolean>(){
        		public Boolean apply(WebDriver driver) {
                    if(driver.findElement(By.xpath("//div[@id='mainpage']/div[2]/span")).getText().matches("^Rolling in 25[\\s\\S]*$")) 
                    	return true;
                    else
                       return false;
               }
        	});
        	 
        	
        	//if it is red we reset count and set win to true to start at 10 later
        	//if we win we reset value also
        	if(driver.findElements(By.xpath(redHistory)).size() != 0 && blackCount > inaRow){
        		win = true;
        		//clear value
        		driver.findElement(By.xpath(clear)).click();
        	}
        	else if(driver.findElements(By.xpath(blackHistory)).size() != 0 && redCount > inaRow){
        		win = true;
        		//clear value
        		driver.findElement(By.xpath(clear)).click();
        	}
        	//If black or zero count goes up
        	if( (driver.findElements(By.xpath(blackHistory)).size() != 0 || 
        			(driver.findElements(By.xpath(zeroHistory)).size() != 0 &&
        			driver.findElements(By.xpath(blackzero)).size() != 0) ) ){
        		blackCount++;
        		redCount = 1;
        	}
        	//if red or zero count goes up
        	else if( (driver.findElements(By.xpath(redHistory)).size() != 0 || 
        			(driver.findElements(By.xpath(zeroHistory)).size() != 0 &&
        			driver.findElements(By.xpath(redzero)).size() != 0 ) ) ){
        		redCount++;
        		blackCount = 1;
        	}
        	//if we won before and count is greater than inaRow we start with 10
        	if( blackCount > inaRow && win == true){
        		//+10
        		driver.findElement(By.xpath(plusTen)).click();
        		//+10
        		driver.findElement(By.xpath(plusTen)).click();
        		//x2
        		driver.findElement(By.xpath(timesTwo)).click();
        		//+10
        		driver.findElement(By.xpath(plusTen)).click();
        		//red
        		driver.findElement(By.xpath(betRed)).click();
        		win = false;
        	}
        	else if(redCount > inaRow && win == true){
        		//+10
        		driver.findElement(By.xpath(plusTen)).click();
        		//+10
        		driver.findElement(By.xpath(plusTen)).click();
        		//x2
        		driver.findElement(By.xpath(timesTwo)).click();
        		//+10
        		driver.findElement(By.xpath(plusTen)).click();
        		
        		//black
        		driver.findElement(By.xpath(betBlack)).click();
        		win = false;
        	}
        	//else black was inaRow+1 in a row or more but we still haven't won we double
        	if( blackCount > inaRow+1 && win == false){
        		//x2
        		driver.findElement(By.xpath(timesTwo)).click();
        		//red
        		driver.findElement(By.xpath(betRed)).click();
        	}
        	else if(redCount > inaRow+1 && win == false){
        		//x2
        		driver.findElement(By.xpath(timesTwo)).click();
        		//black
        		driver.findElement(By.xpath(betBlack)).click();
        	}
        	
        	wait.until(new ExpectedCondition<Boolean>(){
        		public Boolean apply(WebDriver driver) {
                    if(driver.findElement(By.xpath("//div[@id='mainpage']/div[2]/span")).getText().matches("^Rolling in 20[\\s\\S]*$")) 
                    	return true;
                    else
                       return false;
               }
        	});
        	System.out.println("blackCount:" + (blackCount-1));
        	System.out.println("redCount" + (redCount-1));
        	System.out.println(win);
        	wait.until(new ExpectedCondition<Boolean>(){
        		public Boolean apply(WebDriver driver) {
                    if(driver.findElement(By.xpath("//div[@id='mainpage']/div[2]/span")).getText().matches("^Rolling in 0[\\s\\S]*$")) 
                    	return true;
                    else
                       return false;
               }
        	});

        	
        }
        /*1 +10
        2 red
        3 x2
        4 trash*/
        
		//Closing browser
		driver.close();
    }
}