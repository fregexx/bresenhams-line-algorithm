package ru.vsu.bresenham;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TrainingSamples {
    public static final Map<List<Integer>, Integer> trainingSamples = new LinkedHashMap<>();
    public static final Map<List<Integer>, Integer> trainingSamples2 = new LinkedHashMap<>();
    public static final Map<List<Integer>, Integer> digits = new LinkedHashMap<>();
    public static final Map<List<Integer>, Integer> testSamples = new LinkedHashMap<>();

    static {
        trainingSamples.put(Arrays.asList(1, 1, 1, 1, 0, 0, 0, 0, 0, 0), 0);
        trainingSamples.put(Arrays.asList(1, 0, 0, 0, 1, 1, 1, 0, 0, 0), 0);
        trainingSamples.put(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 1, 1, 1), 0);
        trainingSamples.put(Arrays.asList(1, 1, 0, 0, 1, 0, 0, 1, 0, 0), 1);
        trainingSamples.put(Arrays.asList(1, 0, 1, 0, 0, 1, 0, 0, 1, 0), 1);
        trainingSamples.put(Arrays.asList(1, 0, 0, 1, 0, 0, 1, 0, 0, 1), 1);

        trainingSamples2.put(Arrays.asList(0, 0), 0);
        trainingSamples2.put(Arrays.asList(1, 0), 0);
        trainingSamples2.put(Arrays.asList(0, 1), 0);
        trainingSamples2.put(Arrays.asList(1, 1), 1);

        //15 inputs
        digits.put(Arrays.asList(1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1), 0);    //0
        digits.put(Arrays.asList(0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1), 0);    //1
        digits.put(Arrays.asList(1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1), 0);    //2
        digits.put(Arrays.asList(1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1), 0);    //3
        digits.put(Arrays.asList(1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1), 0);    //4
        digits.put(Arrays.asList(1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1), 1);    //5
        digits.put(Arrays.asList(1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1), 0);    //6
        digits.put(Arrays.asList(1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1), 0);    //7
        digits.put(Arrays.asList(1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1), 0);    //8
        digits.put(Arrays.asList(1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1), 0);    //9


        testSamples.put(Arrays.asList(1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1), 0);    //5
        testSamples.put(Arrays.asList(1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1), 0);    //5
        testSamples.put(Arrays.asList(1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1), 0);    //5
        testSamples.put(Arrays.asList(1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1), 0);    //5
        testSamples.put(Arrays.asList(1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1), 0);    //5
        testSamples.put(Arrays.asList(1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1), 0);    //5
    }
}
