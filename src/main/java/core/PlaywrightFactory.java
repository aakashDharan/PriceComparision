package core;

import com.microsoft.playwright.*;

public class PlaywrightFactory {
    private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThreadlocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    public static Page initBrowser(){
        return initBrowser(ConfigReader.browser());
    }
    public static Page initBrowser(String browserName){
        Playwright playwright = Playwright.create();
        playwrightThreadLocal.set(playwright);

        Browser browser = launchBrowser(playwright, browserName);
        browserThreadlocal.set(browser);

        BrowserContext context = browser.newContext( new Browser.NewContextOptions().setViewportSize(1336,786)
                .setBaseURL(ConfigReader.baseUrl())
        );

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        contextThreadLocal.set(context);

        Page page = context.newPage();
        pageThreadLocal.set(page);

        return page;
    }

    private static Browser launchBrowser(Playwright playwright, String browserName){
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(ConfigReader.headless())
                .setSlowMo(ConfigReader.slowMo());

        return switch (browserName.toLowerCase()){
            case "firefox" -> playwright.firefox().launch(options);
            case "webkit" -> playwright.webkit().launch(options);
            default -> playwright.chromium().launch(options);
        };
    }

    public static Page getPage(){
        Page page = pageThreadLocal.get();
        if(page == null){
            throw new IllegalStateException("Page not initialized for tgis thread - call initBrowser() first");
        }
        return page;
    }

    public static BrowserContext getContext(){
        return contextThreadLocal.get();
    }

    public static void saveTraces(String tracePath){
        BrowserContext context = contextThreadLocal.get();
        if(context != null){
            context.tracing().stop(new Tracing.StopOptions().setPath(java.nio.file.Paths.get(tracePath)));
        }
    }

    public static void tearDown(){
        try{
            BrowserContext context = contextThreadLocal.get();

            if(context != null){
                context.close();
            }

            Browser browser = browserThreadlocal.get();
            if(browser != null){
                browser.close();
            }

            Playwright playwright = playwrightThreadLocal.get();

            if(playwright != null) {
                playwright.close();
            }
        } finally{
            contextThreadLocal.remove();
            browserThreadlocal.remove();
            playwrightThreadLocal.remove();
            pageThreadLocal.remove();
        }
    }
 }
