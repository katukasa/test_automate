import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class Tests {

    public void test1() throws IOException{
        Init init = new Init();
        init.getDriver().navigate().to("https://geekbrains.ru/");
        new Waits().wait.until(ExpectedConditions.elementToBeClickable(By.xpath(new Locators().enterButton)));
        init.getDriver().findElement(By.xpath(new Locators().enterButton)).click();
    }

    public void test2(){

    }

}
