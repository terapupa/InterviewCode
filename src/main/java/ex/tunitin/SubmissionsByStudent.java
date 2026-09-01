package ex.tunitin;

import java.util.*;

import static java.util.stream.Collectors.*;

public class SubmissionsByStudent {
    public record Submission(
            String studentId,
            int score
    ) {
    }

//    public static class Submission {
//        private final String studentId;
//        private final int score;
//
//        Submission(String studentId, int score) {
//            this.studentId = studentId;
//            this.score = score;
//        }
//
//        String getStudentId() {
//            return studentId;
//        }
//
//        int getScore() {
//            return score;
//        }
//    }

    public static class SubmissionService {

        public static Map<String, List<Integer>> groupByStudent(
                List<Submission> submissions) {
            Map<String, List<Integer>> result = new HashMap<>();
            for (Submission submission : submissions) {
                result.computeIfAbsent(submission.studentId(), k -> new ArrayList<>()).add(submission.score());
            }
            return result;

//            return submissions.stream()
//                    .collect(groupingBy(Submission::studentId, mapping(Submission::score, toList())));
        }
    }

    public static void main(String[] args) {
        SubmissionsByStudent submissionsByStudent = new SubmissionsByStudent();
        Submission[] submissions = new Submission[]{
                new Submission("A", 10),
                new Submission("B", 20),
                new Submission("A", 30),
                new Submission("C", 40),
                new Submission("B", 50)
        };
        List<Submission> submissionList = Arrays.asList(submissions);
        Map<String, List<Integer>> scores = SubmissionService.groupByStudent(submissionList);
//        var printable = scores
//                .entrySet()
//                .stream()
//                .map(entry -> entry
//                        .getKey() + "-> " + entry
//                        .getValue()
//                        .stream()
//                        .map(Submission::getScore)
//                        .toList())
//                .collect(Collectors.toList());
        System.out.println(scores);
    }

    private void init() {
    }
}
