package simple;

public class LC2660 {
    public int isWinner(int[] player1, int[] player2) {
        int p1 = 0;
        int p2 = 0;
        for (int i = 0, j1 = 0, j2 = 0; i < player1.length; i++, j1--, j2--) {
            p1 += j1 > 0 ? 2 * player1[i] : player1[i];
            p2 += j2 > 0 ? 2 * player2[i] : player2[i];
            if (player1[i] == 10) j1 = 3;
            if (player2[i] == 10) j2 = 3;
        }
        if (p1 == p2) return 0;
        if (p1 > p2) return 1;
        return 2;
    }
}
