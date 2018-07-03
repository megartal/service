package diringo.services.util;

import diringo.services.ota.Jabama;
import diringo.services.ota.OTA;
import diringo.services.ota.Snapptrip;

/**
 * @Author Akbar
 * @DATE 7/4/2018.
 */
public class OTAFactory {
    public static OTA create(String otaName) {
        OTA ota = null;
        switch (otaName) {
            case "snapptrip":
                ota = new Snapptrip();
            case "jabama":
                ota = new Jabama();
        }
        return ota;
    }
}
