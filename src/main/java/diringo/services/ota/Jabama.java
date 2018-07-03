package diringo.services.ota;

import diringo.services.data.RedirectData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author Akbar
 * @DATE 7/4/2018.
 */
@Service
public class Jabama implements OTA {
    @Value("${ota.jabama.url}")
    private String url;

    @Override
    public String getRedirectUrl(RedirectData redirectData) {
        return "";
    }
}
