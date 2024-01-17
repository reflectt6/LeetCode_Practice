package simple;

public class LC2706 {
    public int buyChoco(int[] prices, int money) {
        int m1 = prices[0] <= prices[1] ? prices[0]:prices[1];
        int m2 = prices[0] <= prices[1] ? prices[1]:prices[0];
        for (int i = 2; i < prices.length; i++) {
            if (prices[i] <= m1) {
                m2 = m1; // 难绷，这都能忘
                m1 = prices[i];
            }
            else if (prices[i] <= m2) m2 = prices[i];
        }
        int res = money - m1 - m2;
        return res >= 0 ? res : money;
    }
}
