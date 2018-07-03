package diringo.services.ota;

import diringo.services.data.RedirectData;

/**
 * @Author Akbar
 * @DATE 7/4/2018.
 */
public class Snapptrip implements OTA {
    @Override
    public String getRedirectUrl(RedirectData redirectData) {
        return redirectData.getUrlPattern();
    }
}
