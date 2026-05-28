package ex.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsetSumDpBacktracking {
    public static List<Integer> findSubset(int[] arr, int target) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][target + 1];

        // Сумму 0 можно собрать всегда - пустым множеством
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Заполняем DP-таблицу
        for (int i = 1; i <= n; i++) {
            for (int sum = 1; sum <= target; sum++) {
                // Не берем текущий элемент
                dp[i][sum] = dp[i - 1][sum];

                // Берем текущий элемент, если он не больше sum
                if (sum >= arr[i - 1]) {
                    dp[i][sum] = dp[i][sum] || dp[i - 1][sum - arr[i - 1]];
                }
            }
        }

        // Если target собрать нельзя
        if (!dp[n][target]) {
            return Collections.emptyList();
        }

        // Backtracking: восстанавливаем элементы
        List<Integer> result = new ArrayList<>();
        int i = n;
        int sum = target;

        while (i > 0 && sum > 0) {
            // Если значение пришло сверху, значит текущий элемент не брали
            if (dp[i - 1][sum]) {
                i--;
            } else {
                // Иначе текущий элемент был взят
                result.add(arr[i - 1]);
                sum -= arr[i - 1];
                i--;
            }
        }

        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 17, 21, 23, 32};
        int target = 39;

        List<Integer> subset = findSubset(arr, target);

        if (subset.isEmpty()) {
            System.out.println("Subset not found");
        } else {
            System.out.println("Subset: " + subset);
        }
    }
}
