package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BasePage {

    protected final Page page;

    public BasePage(Page page){
        this.page = page;
    }

    protected Locator locator(String selector){
        return page.locator(selector);
    }

    public String getTitle(){
        return page.title();
    }

    public String getURL(){
        return page.url();
    }

    public void refresh(){
        page.reload();
    }

    public void back(){
        page.goBack();
    }

    public void forward(){
        page.goForward();
    }

    public void navigate(String url){
        page.navigate(url);
    }

    public void click(Locator locator){
        locator.click();
    }

}
