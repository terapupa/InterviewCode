package ex.tunitin;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeOverlappingIntervals {

    private static int applyAsInt(Interval interval) {
        return interval.start;
    }

    record Interval(int start, int end) {
    }

    public static void main(String[] args) {
        MergeOverlappingIntervals mergeOverlappingIntervals = new MergeOverlappingIntervals();
        List<Interval> intervals = List.of(
                new Interval(15, 18),
                new Interval(1, 9),
                new Interval(2, 4),
                new Interval(8, 10),
                new Interval(9, 12)
        );
        List<Interval> mergedIntervals = mergeOverlappingIntervals.merge(intervals);
        System.out.println("Merged intervals: ");
        for (Interval interval : mergedIntervals) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }
    }

    private List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return List.of();
        }
        List<Interval> result = new LinkedList<>();
        List<Interval> sortedIntervals = new LinkedList<>(intervals);
        sortedIntervals.sort(Comparator.comparingInt(Interval::start).thenComparing(Interval::end));

        Interval currentInterval = sortedIntervals.get(0);
        for (int i = 1; i < sortedIntervals.size(); i++) {
            Interval nextInterval = sortedIntervals.get(i);
            if (currentInterval.end() >= nextInterval.start()) {
                currentInterval = new Interval(currentInterval.start(),
                                               Math.max(currentInterval.end(), nextInterval.end()));
            } else {
                result.add(currentInterval);
                currentInterval = nextInterval;
            }
        }
        result.add(currentInterval);
        return result;
    }
}


