package diringo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Akbar
 * @since 7/4/2018
 */
public class GuestRoomMatch {
    public static Map<String, ArrayList<ArrayList<Integer>>> match = new HashMap<>();

    static {
        ArrayList<ArrayList<Integer>> m11 = new ArrayList<>();
        m11.add(new ArrayList<>(Arrays.asList(1)));
        match.put("11", m11);

        ArrayList<ArrayList<Integer>> m21 = new ArrayList<>();
        m21.add(new ArrayList<>(Arrays.asList(2)));
        match.put("21", m21);

        ArrayList<ArrayList<Integer>> m31 = new ArrayList<>();
        m31.add(new ArrayList<>(Arrays.asList(3)));
        match.put("31", m31);

        ArrayList<ArrayList<Integer>> m41 = new ArrayList<>();
        m41.add(new ArrayList<>(Arrays.asList(4)));
        match.put("41", m41);

        ArrayList<ArrayList<Integer>> m51 = new ArrayList<>();
        m51.add(new ArrayList<>(Arrays.asList(5)));
        match.put("51", m51);

        ArrayList<ArrayList<Integer>> m61 = new ArrayList<>();
        m61.add(new ArrayList<>(Arrays.asList(6)));
        match.put("61", m61);

        ArrayList<ArrayList<Integer>> m71 = new ArrayList<>();
        m71.add(new ArrayList<>(Arrays.asList(7)));
        match.put("71", m71);

        ArrayList<ArrayList<Integer>> m81 = new ArrayList<>();
        m81.add(new ArrayList<>(Arrays.asList(8)));
        match.put("81", m81);

        ArrayList<ArrayList<Integer>> m91 = new ArrayList<>();
        m91.add(new ArrayList<>(Arrays.asList(9)));
        match.put("91", m91);

        ArrayList<ArrayList<Integer>> m22 = new ArrayList<>();
        m22.add(new ArrayList<>(Arrays.asList(1, 1)));
        match.put("22", m22);

        ArrayList<ArrayList<Integer>> m32 = new ArrayList<>();
        m32.add(new ArrayList<>(Arrays.asList(1, 2)));
        match.put("32", m32);

        ArrayList<ArrayList<Integer>> m33 = new ArrayList<>();
        m33.add(new ArrayList<>(Arrays.asList(1, 1, 1)));
        match.put("33", m33);

        ArrayList<ArrayList<Integer>> m42 = new ArrayList<>();
        m42.add(new ArrayList<>(Arrays.asList(1, 3)));
        m42.add(new ArrayList<>(Arrays.asList(2, 2)));
        match.put("42", m42);

        ArrayList<ArrayList<Integer>> m43 = new ArrayList<>();
        m43.add(new ArrayList<>(Arrays.asList(1, 1, 2)));
        match.put("43", m43);

        ArrayList<ArrayList<Integer>> m44 = new ArrayList<>();
        m44.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1)));
        match.put("44", m44);

        ArrayList<ArrayList<Integer>> m52 = new ArrayList<>();
        m52.add(new ArrayList<>(Arrays.asList(1, 4)));
        m52.add(new ArrayList<>(Arrays.asList(2, 3)));
        match.put("52", m52);

        ArrayList<ArrayList<Integer>> m53 = new ArrayList<>();
        m53.add(new ArrayList<>(Arrays.asList(1, 1, 3)));
        m53.add(new ArrayList<>(Arrays.asList(1, 2, 2)));
        match.put("53", m53);

        ArrayList<ArrayList<Integer>> m54 = new ArrayList<>();
        m54.add(new ArrayList<>(Arrays.asList(1, 1, 1, 2)));
        match.put("54", m54);

        ArrayList<ArrayList<Integer>> m55 = new ArrayList<>();
        m55.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        match.put("55", m55);

        ArrayList<ArrayList<Integer>> m62 = new ArrayList<>();
        m62.add(new ArrayList<>(Arrays.asList(1, 5)));
        m62.add(new ArrayList<>(Arrays.asList(2, 4)));
        m62.add(new ArrayList<>(Arrays.asList(3, 3)));
        match.put("62", m62);

        ArrayList<ArrayList<Integer>> m63 = new ArrayList<>();
        m63.add(new ArrayList<>(Arrays.asList(1, 1, 4)));
        m63.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        m63.add(new ArrayList<>(Arrays.asList(2, 2, 2)));
        match.put("63", m63);

        ArrayList<ArrayList<Integer>> m64 = new ArrayList<>();
        m64.add(new ArrayList<>(Arrays.asList(1, 1, 1, 3)));
        m64.add(new ArrayList<>(Arrays.asList(1, 1, 2, 2)));
        match.put("64", m64);

        ArrayList<ArrayList<Integer>> m65 = new ArrayList<>();
        m65.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 2)));
        match.put("65", m65);

        ArrayList<ArrayList<Integer>> m66 = new ArrayList<>();
        m66.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1)));
        match.put("66", m66);

        ArrayList<ArrayList<Integer>> m72 = new ArrayList<>();
        m72.add(new ArrayList<>(Arrays.asList(2, 5)));
        m72.add(new ArrayList<>(Arrays.asList(1, 6)));
        m72.add(new ArrayList<>(Arrays.asList(3, 4)));
        match.put("72", m72);

        ArrayList<ArrayList<Integer>> m73 = new ArrayList<>();
        m73.add(new ArrayList<>(Arrays.asList(1, 1, 5)));
        m73.add(new ArrayList<>(Arrays.asList(1, 2, 4)));
        m73.add(new ArrayList<>(Arrays.asList(1, 3, 3)));
        m73.add(new ArrayList<>(Arrays.asList(2, 2, 3)));
        match.put("73", m73);

        ArrayList<ArrayList<Integer>> m74 = new ArrayList<>();
        m74.add(new ArrayList<>(Arrays.asList(1, 1, 1, 4)));
        m74.add(new ArrayList<>(Arrays.asList(1, 1, 2, 3)));
        m74.add(new ArrayList<>(Arrays.asList(1, 2, 2, 2)));
        match.put("74", m74);

        ArrayList<ArrayList<Integer>> m75 = new ArrayList<>();
        m75.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 3)));
        m75.add(new ArrayList<>(Arrays.asList(1, 1, 1, 2, 2)));
        match.put("75", m75);

        ArrayList<ArrayList<Integer>> m76 = new ArrayList<>();
        m76.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 2)));
        match.put("76", m76);

        ArrayList<ArrayList<Integer>> m77 = new ArrayList<>();
        m77.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1)));
        match.put("77", m77);

        ArrayList<ArrayList<Integer>> m82 = new ArrayList<>();
        m82.add(new ArrayList<>(Arrays.asList(1, 7)));
        m82.add(new ArrayList<>(Arrays.asList(2, 6)));
        m82.add(new ArrayList<>(Arrays.asList(3, 5)));
        m82.add(new ArrayList<>(Arrays.asList(4, 4)));
        match.put("82", m82);

        ArrayList<ArrayList<Integer>> m83 = new ArrayList<>();
        m83.add(new ArrayList<>(Arrays.asList(1, 1, 6)));
        m83.add(new ArrayList<>(Arrays.asList(1, 2, 5)));
        m83.add(new ArrayList<>(Arrays.asList(1, 3, 4)));
        m83.add(new ArrayList<>(Arrays.asList(2, 2, 4)));
        m83.add(new ArrayList<>(Arrays.asList(2, 3, 3)));
        match.put("83", m83);

        ArrayList<ArrayList<Integer>> m84 = new ArrayList<>();
        m84.add(new ArrayList<>(Arrays.asList(1, 2, 2, 3)));
        m84.add(new ArrayList<>(Arrays.asList(1, 1, 3, 3)));
        m84.add(new ArrayList<>(Arrays.asList(1, 1, 1, 5)));
        m84.add(new ArrayList<>(Arrays.asList(1, 1, 2, 4)));
        m84.add(new ArrayList<>(Arrays.asList(2, 2, 2, 2)));
        match.put("84", m84);

        ArrayList<ArrayList<Integer>> m85 = new ArrayList<>();
        m85.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 4)));
        m85.add(new ArrayList<>(Arrays.asList(1, 1, 1, 2, 3)));
        m85.add(new ArrayList<>(Arrays.asList(1, 1, 2, 2, 2)));
        m85.add(new ArrayList<>(Arrays.asList(1, 1, 2, 2, 2)));
        match.put("85", m85);

        ArrayList<ArrayList<Integer>> m86 = new ArrayList<>();
        m86.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 3)));
        m86.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 2, 2)));
        match.put("86", m86);

        ArrayList<ArrayList<Integer>> m87 = new ArrayList<>();
        m87.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 2)));
        match.put("87", m87);

        ArrayList<ArrayList<Integer>> m88 = new ArrayList<>();
        m88.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1)));
        match.put("88", m88);

        ArrayList<ArrayList<Integer>> m92 = new ArrayList<>();
        m92.add(new ArrayList<>(Arrays.asList(1, 8)));
        m92.add(new ArrayList<>(Arrays.asList(2, 7)));
        m92.add(new ArrayList<>(Arrays.asList(3, 6)));
        m92.add(new ArrayList<>(Arrays.asList(4, 5)));
        match.put("92", m92);

        ArrayList<ArrayList<Integer>> m93 = new ArrayList<>();
        m93.add(new ArrayList<>(Arrays.asList(1, 1, 7)));
        m93.add(new ArrayList<>(Arrays.asList(1, 2, 6)));
        m93.add(new ArrayList<>(Arrays.asList(1, 3, 5)));
        m93.add(new ArrayList<>(Arrays.asList(1, 4, 4)));
        m93.add(new ArrayList<>(Arrays.asList(2, 2, 5)));
        m93.add(new ArrayList<>(Arrays.asList(2, 3, 4)));
        m93.add(new ArrayList<>(Arrays.asList(3, 3, 3)));
        match.put("93", m93);

        ArrayList<ArrayList<Integer>> m94 = new ArrayList<>();
        m94.add(new ArrayList<>(Arrays.asList(1, 1, 1, 6)));
        m94.add(new ArrayList<>(Arrays.asList(1, 1, 2, 5)));
        m94.add(new ArrayList<>(Arrays.asList(1, 1, 3, 4)));
        m94.add(new ArrayList<>(Arrays.asList(1, 2, 2, 4)));
        m94.add(new ArrayList<>(Arrays.asList(1, 2, 3, 3)));
        m94.add(new ArrayList<>(Arrays.asList(2, 2, 2, 3)));
        match.put("94", m94);

        ArrayList<ArrayList<Integer>> m95 = new ArrayList<>();
        m95.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 5)));
        m95.add(new ArrayList<>(Arrays.asList(1, 1, 1, 2, 4)));
        m95.add(new ArrayList<>(Arrays.asList(1, 1, 1, 3, 3)));
        m95.add(new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3)));
        m95.add(new ArrayList<>(Arrays.asList(1, 2, 2, 2, 2)));
        match.put("95", m95);

        ArrayList<ArrayList<Integer>> m96 = new ArrayList<>();
        m96.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 4)));
        m96.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 2, 3)));
        m96.add(new ArrayList<>(Arrays.asList(1, 1, 1, 2, 2, 2)));
        match.put("96", m96);

        ArrayList<ArrayList<Integer>> m97 = new ArrayList<>();
        m97.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 3)));
        m97.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 2, 2)));
        match.put("97", m97);

        ArrayList<ArrayList<Integer>> m98 = new ArrayList<>();
        m98.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 2)));
        match.put("98", m98);

        ArrayList<ArrayList<Integer>> m99 = new ArrayList<>();
        m99.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1)));
        match.put("99", m99);
    }
}
