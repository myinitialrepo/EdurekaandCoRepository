package com.selenium.util;

import com.selenium.constants.BrowserType;

import java.util.HashMap;
import java.util.Map;

public class WebDriverUtil {

    private WebDriverUtil(){}

    private static final Map<BrowserType, String> BROWSER_LOOKUP_KEYS;

    static {
        BROWSER_LOOKUP_KEYS = new HashMap<>();
        BROWSER_LOOKUP_KEYS.put(BrowserType.CHROME, "webdriver.chrome.driver");
        BROWSER_LOOKUP_KEYS.put(BrowserType.FIRE_FOX, "webdriver.gecko.driver");
    }

    public static String getLookUpKey(BrowserType type){
        return BROWSER_LOOKUP_KEYS.get(type);
    }

    public static BrowserType parseBrowserType(String browser){
        if(browser == null || browser.trim().isEmpty())
            throw new IllegalArgumentException("browser type cannot be empty/null.");
        return BrowserType.valueOf(browser.toUpperCase());
    }
}
