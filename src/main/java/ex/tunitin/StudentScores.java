package ex.tunitin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentScores {

    public record Student (
            String id,
            int score
    ) {}

    public List<Student> getStudents() {
        return List.of(
                new Student("A", 10),
                new Student("B", 20),
                new Student("A", 30),
                new Student("C", 40),
                new Student("B", 50)
        );
    }

    public Map<String, Integer> calculateScores(
            List<Student> students) {
        return students.stream().collect(Collectors.groupingBy(Student::id, Collectors.summingInt(Student::score)));
    }

    public static void main(String[] args) {
        StudentScores studentScores = new StudentScores();
        List<Student> students = studentScores.getStudents();
        Map<String, Integer> scores = studentScores.calculateScores(students);
        System.out.println(scores);
    }
}
