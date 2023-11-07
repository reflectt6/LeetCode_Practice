package simple;

import utils.Solution;

//    如果一个字符串不含重复字符，我们称为好字符串
//    给定一个字符串s
//    返回s中长度为3的好字符串的数量
//    重复出现的字串算多个
public class Subject1876s implements Solution<Integer,String> {
    public static void main(String[] args) {
        Subject1876s instance = new Subject1876s();
        System.out.println(instance.countGoodSubstring("aababcabc"));
    }
    public int countGoodSubstring(String s){
        int count = 0;
        int front = 0;
        int middle = 1;
        int behind = 2;
        int end = s.length();
        for (int i = behind; i < end; front++,middle++,behind++,i=behind) {
            if (s.charAt(front) == s.charAt(middle)||s.charAt(front) == s.charAt(behind)||s.charAt(middle) == s.charAt(behind)){
                continue;
            }
            count++;
        }
        return count;
    }

    @Override
    public Integer exec(String s) {
        return this.countGoodSubstring(s);
    }
}
