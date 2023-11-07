package middle;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Leetcode2284 {
    public static void main(String[] args) {
        String[] me = new String[]{"Hello userTwooo","Hi userThree","Wonderful day Alice","Nice day userThree"};
        String[] senders = new String[]{"Alice","userTwo","userThree","Alice"};
        System.out.println(largestWordCount(me, senders));
    }

    public static String largestWordCount(String[] messages, String[] senders) {
        int[] ms = Arrays.stream(messages).mapToInt(m -> m.split(" ").length).toArray();
        TreeMap<String, Integer> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        int max = 0;
        for (int i = 0; i < senders.length; i++) {
            String s = senders[i];
            int v = map.getOrDefault(s, 0) + ms[i];
            map.put(s, v);
            max = Math.max(max, v);
        }
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (e.getValue() == max) {
                return e.getKey();
            }
        }
        // never
        return null;
    }
}
