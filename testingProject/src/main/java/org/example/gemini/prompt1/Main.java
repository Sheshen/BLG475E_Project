package org.example.gemini.prompt1;
import java.io.Console;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution s = new org.example.gemini.prompt1.Solution();
        List<Boolean> correct = Arrays.asList(
                s.separateParenGroups("(()()) ((())) () ((())()())").equals(Arrays.asList(
                        "(()())", "((()))", "()", "((())()())"
                )),
                s.separateParenGroups("() (()) ((())) (((())))").equals(Arrays.asList(
                        "()", "(())", "((()))", "(((())))"
                )),
                s.separateParenGroups("(()(())((())))").equals(Arrays.asList(
                        "(()(())((())))"
                )),
                s.separateParenGroups("( ) (( )) (( )( ))").equals(Arrays.asList("()", "(())", "(()())"))
        );
        if (correct.contains(false)) {
            throw new AssertionError();
        }
    }

}

