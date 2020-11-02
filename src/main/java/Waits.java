import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.jvm.hotspot.utilities.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Waits {
    public static int small_wait = 3;
    public static int medium_wait = 10;
    public static int big_wait = 30;
    public static int polling_time = 1;

    WebDriverWait wait = new WebDriverWait(Init.getDriver(), medium_wait);

    public long Now(){
        return new Date().getTime();
    }

    public void staticWait(double pollingTime){
        try {
            Thread.sleep((int) (pollingTime * 1000));
        } catch (InterruptedException interrupt) {
            System.out.println("Problem with the stream.");
        }
    }

    public List<WebElement> waitAndGetWebElementsLite(String xpath, int timeout, double pollingTime){
        List<WebElement> webElements = new ArrayList<>();
        long startTime = Now();
        while (((Now() - startTime) / 1000) < timeout) {
            try {
                webElements = Init.getDriver().findElements(By.xpath(xpath));
            } catch (InvalidSelectorException e){
                System.err.println("Wrong locator syntax.");
                throw e;
            }
            if ((!webElements.isEmpty()) && webElements.get(0).isEnabled()) {
                return webElements;
            }
            staticWait(pollingTime);
        }
        return webElements;
    }

    public void waitNotElement(String xpath, int timeout, double pollingTime, int startWaitForPresent){
        List<WebElement> elements = null;
        List<WebElement> invisibleElements = null;
        if (waitAndGetWebElementsLite(xpath, startWaitForPresent, pollingTime).isEmpty()){
            System.out.println("Elements " + xpath + " are invisible.");
            return;
        }

        long startTime = Now();
        while (!(((Now() - startTime) / 1000) > timeout)){
            staticWait(pollingTime);
            elements = Init.getDriver().findElements(By.xpath(xpath));
            invisibleElements = elements.stream().filter(elem -> !elem.isDisplayed()).collect(Collectors.toList());
            if (elements.isEmpty() || (elements.size() == invisibleElements.size())){
                break;
            }
        }
        Assert.assertTrue("Elements " + xpath + " are visible.", elements.isEmpty());

    }

}

