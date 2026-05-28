package ex.code;

import java.util.List;

public class test123 {

    private static final double PEAK_DIFF_THRESHOLD = 5.0;

    public static int countPeak(List<Double> values) {
        if (values == null || values.size() < 3) {
            return 0;
        }

        int peakCount = 0;
        for (int i = 1; i < values.size() - 1; i++) {
            Double prev = values.get(i - 1);
            Double curr = values.get(i);
            Double next = values.get(i + 1);

            // Skip incomplete windows instead of failing on null values.
            if (prev == null || curr == null || next == null) {
                continue;
            }

            boolean isPeak = curr - prev >= PEAK_DIFF_THRESHOLD
                    && curr - next >= PEAK_DIFF_THRESHOLD;
            boolean isValley = prev - curr >= PEAK_DIFF_THRESHOLD
                    && next - curr >= PEAK_DIFF_THRESHOLD;

            if (isPeak || isValley) {
                peakCount++;
            }
        }

        return peakCount;
    }
}
