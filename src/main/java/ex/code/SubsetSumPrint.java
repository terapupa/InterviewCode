package ex.code;

import java.util.ArrayList;
import java.util.List;

public class SubsetSumPrint {
    public static boolean findSubset(int[] arr, int index, int target, List<Integer> path) {
        if (target == 0) {
            return true;
        }

        if (index == arr.length || target < 0) {
            return false;
        }

        path.add(arr[index]);
        if (findSubset(arr, index + 1, target - arr[index], path)) {
            return true;
        }
        path.remove(path.size() - 1);

        return findSubset(arr, index + 1, target, path);
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 17, 21, 23, 32};
        int target = 39;

        List<Integer> result = new ArrayList<>();
        boolean found = findSubset(arr, 0, target, result);

        if (found) {
            System.out.println(result);
        } else {
            System.out.println("No subset found");
        }
    }
}
