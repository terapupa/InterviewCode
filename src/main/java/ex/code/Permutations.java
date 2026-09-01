package ex.code;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public List<List<String>> permute(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        boolean[] visited = new boolean[strings.length];
        permute(strings, new ArrayList<>(), visited, res);
        return res;
    }

    private <E> void permute(String[] strings, ArrayList<String> current, boolean[] visited, List<List<String>> res) {
        if (current.size() == strings.length) {
            res.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < strings.length; i++) {
            if (!visited[i]) {
                current.add(strings[i]);
                visited[i] = true;
                permute(strings, current, visited, res);
                current.remove(current.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        var result = permutations.permute(new String[]{"sa","fs","hd","jjf"});
        result.stream().map(l -> String.join("", l)).forEach(System.out::println);
    }
}
