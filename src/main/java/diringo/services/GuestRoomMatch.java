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
        m52.add(new ArrayList<>(Arrays.asList(2, 3)));
        m52.add(new ArrayList<>(Arrays.asList(1, 4)));
        match.put("52", m52);

        ArrayList<ArrayList<Integer>> m53 = new ArrayList<>();
        m53.add(new ArrayList<>(Arrays.asList(1, 1, 3)));
        m53.add(new ArrayList<>(Arrays.asList(1, 2, 2)));
        match.put("53", m53);

        ArrayList<ArrayList<Integer>> m54 = new ArrayList<>();
        m54.add(new ArrayList<>(Arrays.asList(1, 4)));
        match.put("54", m54);

        ArrayList<ArrayList<Integer>> m55 = new ArrayList<>();
        m55.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)));
        match.put("55", m55);
    }
}
