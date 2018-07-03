package diringo.services.ota;

import diringo.services.data.RedirectData;

/**
 * @Author Akbar
 * @DATE 7/4/2018.
 */
public interface OTA {
    String getRedirectUrl(RedirectData redirectData);
}
