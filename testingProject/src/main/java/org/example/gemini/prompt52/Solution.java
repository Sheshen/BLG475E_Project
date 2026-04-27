package org.example.gemini.prompt52;/* @Authors
Student Names: Mehmet Abdullah Şeşen, Sezai Gökalp, Merve Nur Çalık
Student IDs: 150220029, 150220713, 150180096
*/
import java.util.*;
import java.lang.*;


class Solution {
    /**
     Return True if all numbers in the list l are below threshold t.
     >>> belowThreshold(Arrays.asList(1, 2, 4, 10), 100)
     true
     >>> belowThreshold(Arrays.asList(1, 20, 4, 10), 5)
     false
     */
    public boolean belowThreshold(List<Integer> l, int t) {
        for (int e : l) {
            if (e >= t) {
                return false;
            }
        }
        return true;
    }
}
