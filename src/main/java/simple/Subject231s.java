package simple;

import utils.Solution;

/**
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 如果存在一个整数 x 使得n == 2^x ，则认为 n 是 2 的幂次方。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-two
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subject231s implements Solution<Boolean,Integer> {
//    public boolean isPowerOfTwo(int n) {
//        do {
//            if (n == 1)return true;
//            if ((n&1) == 1) {
//                return false;
//            }
//            n/=2;
//        }while (n >= 1);
//        return false;
//    }
//    上面是我的方法，想到用位运算了，但是还是不够简单
    public boolean isPowerOfTwo(int n) {
        return n>0&& (n&(n-1))==0;
    }

    @Override
    public Boolean exec(Integer n) {
        return this.isPowerOfTwo(n);
    }
}
