package io.loop.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoopcampUtils {
/**
     *
     *returns any field from a given name
     * @param  driver;
     * @param name;
     * @param field;
     * author SJ
     * @return
*/
//here is my custom method
// option 2 of the same method is in PizzaOrderWebTableUtil

    public static String returnAnyFrieldFromTable(WebDriver driver, String name, String field) throws InterruptedException {

        WebElement element = null;
        String xpath = "";

        switch (field.toLowerCase()){
            case "type":
                xpath = "//td[2][text()='" + name + "']/following-sibling::td[1]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "amount" :
                xpath = "//td[2][text()='" + name + "']/following-sibling::td[2]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case  "date" :
                xpath = "//td[2][text()='" + name + "']/following-sibling::td[3]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "street":
                xpath = "//td[2][text()='" + name + "']/following-sibling::td[4]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "city":
                xpath = "//td[2][text()='" + name + "']/following-sibling::td[5]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "state":
                xpath = "//td[2][text()='" + name +"']/following-sibling::td[6]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "zip":
                xpath = "//td[2][text()='" + name + "']/following-sibling::td[7]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "card":
                xpath = "//td[2][text()='" + name + "']/following-sibling::td[8]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "card number":
                xpath = "//td[2][text()='" + name + "']/following-sibling::td[9]";
                element = driver.findElement(By.xpath(xpath));
                break;
            case "exp":
                xpath = "//td[2][text()='" + name + "']/following-sibling::td[10]";
                element = driver.findElement(By.xpath(xpath));
                break;
            default:
                throw new InterruptedException("There is no such a field: " + field);
        }

        return element.getText().trim();
    }
}
