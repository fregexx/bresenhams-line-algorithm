package ru.vsu.bresenham;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class PerceptronOld {
    private final double LEARNING_RATE = 0.5;
    private final ActivationFunction activationFunction = new ActivationFunction();
    private List<Double> weights = new ArrayList<>();
    private Integer output;

    public PerceptronOld(int size) {
        randomWeights(size);
    }

    public void train(Map<List<Integer>, Integer> trainingSamples) {
        trainingSamples.forEach((inputs, value)-> {
            Integer out = calc(inputs);
            int error = value - out;
            if (error != 0) {
                adjustWeights(inputs, error);
            }
        });
    }

    public Integer calc(List<Integer> inputs) {
        double sum = calcWeightedSum(inputs);
        return activationFunction.apply(sum);
    }

    private double calcWeightedSum(List<Integer> inputs) {
        double sum = 0;
        for (int i = 0; i < inputs.size(); i++) {
            sum += inputs.get(i) * weights.get(i);
        }
        return sum;
    }

    private void adjustWeights(List<Integer> inputs, int error) {
        for (int i = 0; i < weights.size(); i++) {
            this.weights.set(i, weights.get(i) + LEARNING_RATE * error * inputs.get(i));
        }
    }

    private void randomWeights(int size) {
        for (int i = 0; i < size; i++) {
            weights.add(i, Math.random());
        }
    }
}
