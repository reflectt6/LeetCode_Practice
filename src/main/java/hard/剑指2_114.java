package hard;

public class 剑指2_114 {
    public static void main(String[] args) {
//        String[] words = new String[]{"wrt","wrf","er","ett","rftt"};
        String[] words = new String[]{"zy","zx"};
        System.out.println(alienOrder(words));
    }
    public static String alienOrder(String[] words) {
        String[] r1 = new String[words.length-1];
        a:for (int i = 0; i < words.length-1; i++) {
            String s1 = words[i];
            String s2 = words[i+1];
            int num = Math.min(s1.length(),s2.length());
            b:for (int j = 0; j < num; j++) {
                if (s1.charAt(j)!=s2.charAt(j)){
                    r1[i]=s1.substring(0,j)+String.valueOf(s1.charAt(j))+String.valueOf(s2.charAt(j));
                    break b;
                }
            }
        }
        a:for (int i = 0; i < words.length-2; i++) {
            if (r1[i].length()==0){continue a;}
            b:for (int j = i+1; j < words.length-1; j++) {
                if (r1[j].length()==0){
                    continue b;
                } else {
                    int last = r1[j].length()-1;
                    if (r1[i].charAt(0)==r1[j].charAt(last)){
                        if (new StringBuilder(r1[i]).reverse().toString().equals(r1[j])){
                            r1[i]="";
                            r1[j]="";
                            continue b;
                        }
                        r1[j]=r1[j]+(r1[i].substring(1,last+1));
                        r1[i]="";
                        continue a;
                    }
                    last = r1[i].length()-1;
                    if (r1[i].charAt(last)==r1[j].charAt(0)){
                        if (new StringBuilder(r1[i]).reverse().toString().equals(r1[j])){
                            r1[i]="";
                            r1[j]="";
                            continue b;
                        }
                        r1[j]=(r1[i].substring(0,last))+r1[j];
                        r1[i]="";
                        continue a;
                    }
                }
            }
        }
        if (r1[0]==null){
            return words[0];
        }
        for (int i = 0; i < words.length-1; i++) {
            if (r1[i].length()!=0)return r1[i];
        }
        return "";
    }
}
