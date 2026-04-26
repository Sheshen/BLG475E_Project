/* @Authors
Student Names: Mehmet Abdullah Şeşen, Sezai Gökalp, Merve Nur Çalık
Student IDs: 150220029
*/
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Filter an input list of strings only for ones that contain given substring
    >>> filterBySubstring(List.of(), "a")
    []
    >>> filterBySubstring(Arrays.asList("abc", "bacd", "cde", "array"), "a")
    ["abc", "bacd", "array"]
     */
    public List<String> filterBySubstring(List<String> strings, String substring) {
        List<String> result = new ArrayList<>();
        for (String x : strings) {
            if (x.contains(substring)) {
                result.add(x);
            }
        }
        return result;
    }
}
