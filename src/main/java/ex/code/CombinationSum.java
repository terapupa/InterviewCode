package ex.code;
/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output:
[
[1,2,2],
[5]
]
*/

import java.util.*;

public class CombinationSum {
    static class State {
        int startIndex;
        List<Integer> current;

        State(int startIndex, List<Integer> current) {
            this.startIndex = startIndex;
            this.current = current;
        }
    }

    public List<List<Integer>> combinationSum(List<Integer> candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<State> stack = new Stack<>();
        stack.push(new State(0, new ArrayList<>()));

        while (!stack.empty()) {
            State s =  stack.pop();
            if (!s.current.isEmpty() /*&& s.current.stream().mapToInt(Integer::intValue).sum() == target*/) {
                res.add(s.current);
            }
            for (int i = s.startIndex; i < candidates.size() - 1; i++) {
                if (i <= s.startIndex || candidates.get(i) != (int)candidates.get(i - 1)) {
                    var newArray = new ArrayList<>(s.current);
                    newArray.add(candidates.get(i));
                    stack.push(new State(i + 1, newArray));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        List<Integer> candidates = List.of(10, 1, 2, 7, 6, 1, 5);
        int target = 8;
        System.out.println(cs.combinationSum(candidates, target));
    }
}
