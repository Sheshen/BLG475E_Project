package org.example.claude.prompt24;/* @Authors
Student Names: Mehmet Abdullah Şeşen, Sezai Gökalp, Merve Nur Çalık
Student IDs: 150220029, 150220713, 150180096
*/
import java.util.*;
import java.lang.*;

class Solution {
    /**
    For a given number n, find the largest number that divides n evenly, smaller than n
    >>> largestDivisor(15)
    5
     */
    public int largestDivisor(int n) {
        for (int i = n - 1; i > 0; i--) {
            if (n % i == 0) {
                return i;
            }
        }
        return 1;
    }
}
