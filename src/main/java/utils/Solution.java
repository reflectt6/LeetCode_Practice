package utils;

import java.util.List;

public interface Solution<T,M> {
    default  <T> T exec(M s){
        return null;
    };
    default  <T> T exec(){
        return null;
    }
    default  List<List<Integer>> exec(Integer[] n){
        return null;
    };
}
