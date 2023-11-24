package middle;

public class LC1410 {
    public String entityParser0(String text) {
        StringBuilder sb = new StringBuilder();
        int n = text.length();
        for (int i = 0; i < n; i++) {
            if (text.charAt(i) != '&') sb.append(text.charAt(i));
            else {
                if (i + 3 >= n) {
                    sb.append(text.charAt(i));
                    continue;
                } else {
                    String l4 = text.substring(i, i + 4);
                    if (l4.equals("&gt;")) {
                        sb.append('>');
                        i = i + 3;
                        continue;
                    } else if (l4.equals("&lt;")) {
                        sb.append('<');
                        i = i + 3;
                        continue;
                    }
                }
                if (i + 4 >= n) {
                    sb.append(text.charAt(i));
                    continue;
                } else {
                    String l5 = text.substring(i, i + 5);
                    if (l5.equals("&amp;")) {
                        sb.append('&');
                        i = i + 4;
                        continue;
                    }
                }
                if (i + 5 >= n) {
                    sb.append(text.charAt(i));
                    continue;
                } else {
                    String l6 = text.substring(i, i + 6);
                    if (l6.equals("&quot;")) {
                        sb.append('"');
                        i = i + 5;
                        continue;
                    } else if (l6.equals("&apos;")) {
                        sb.append('\'');
                        i = i + 5;
                        continue;
                    }
                }
                if (i + 6 >= n) {
                    sb.append(text.charAt(i));
                    continue;
                } else {
                    String l7 = text.substring(i, i + 7);
                    if (l7.equals("&frasl;")) {
                        sb.append("/");
                        i += 6;
                        continue;
                    }
                }
                sb.append(text.charAt(i));
            }
        }
        return sb.toString();
    }

    static String[][][] parseMatrix;

    static {
        parseMatrix = new String[8][][];

        parseMatrix[4] = new String[2][];
        parseMatrix[4][0] = new String[]{"&gt;", ">"};
        parseMatrix[4][1] = new String[]{"&lt;", "<"};

        parseMatrix[5] = new String[1][];
        parseMatrix[5][0] = new String[]{"&amp;", "&"};

        parseMatrix[6] = new String[2][];
        parseMatrix[6][0] = new String[]{"&quot;", "\""};
        parseMatrix[6][1] = new String[]{"&apos;", "'"};

        parseMatrix[7] = new String[1][];
        parseMatrix[7][0] = new String[]{"&frasl;", "/"};
    }

    public String entityParser(String text) {
        StringBuilder sb = new StringBuilder();
        int n = text.length();
        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            if (c != '&') {
                sb.append(c);
            } else {
                int index = handleConvert(sb, text, i);
                if (index >= 0) {
                    i = index;
                } else {
                    sb.append(text.charAt(i));
                }
            }
        }
        return sb.toString();
    }

    public int handleConvert(StringBuilder sb, String text, int i) {
        int n = text.length();
        for (int candidateLength = 4; candidateLength <= 7; candidateLength++) {
            if (i + candidateLength > n) {
                sb.append(text.charAt(i));
                return i;
            }
            String tmp = text.substring(i, i + candidateLength);
            for (String[] matrix : parseMatrix[candidateLength]) {
                if (tmp.equals(matrix[0])) {
                    sb.append(matrix[1]);
                    return i + candidateLength - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LC1410 lc1410 = new LC1410();
//        System.out.println(lc1410.entityParser("&amp; is an HTML entity but &ambassador; is not."));
//        System.out.println(lc1410.entityParser("&&gt;"));
        System.out.println(lc1410.entityParser("&&&"));
    }
}
