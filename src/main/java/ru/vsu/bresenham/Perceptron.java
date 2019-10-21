package ru.vsu.bresenham;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Perceptron {
    private final double LEARNING_RATE = 0.5;
    private final ActivationFunction activationFunction = new ActivationFunction();
    private List<Double> weights = new ArrayList<>();
    private Integer output;
    private int bias = 5;

    public Perceptron(int inputsCount) {
        initWeights(inputsCount);
    }

    public void train(Map<List<Integer>, Integer> trainingSamples) {
        trainingSamples.forEach((inputs, value)-> {
            if(value.equals(1)){
                if(!identify(inputs)){
                    adjustWeights(inputs, 1);
                }
            } else {
                if(identify(inputs)){
                    adjustWeights(inputs, -1);
                }
            }
        });
    }

    public boolean identify(List<Integer> inputs){
        double sum = 0;
        for (int i = 0; i < inputs.size(); i++) {
            sum += inputs.get(i) * weights.get(i);
        }
        return sum >= bias;
    }

    private void adjustWeights(List<Integer> inputs, int error) {
        for (int i = 0; i < weights.size(); i++) {
            this.weights.set(i, weights.get(i) + LEARNING_RATE * error * inputs.get(i));
        }
    }

    private void initWeights(int size) {
        for (int i = 0; i < size; i++) {
            weights.add(i, 0.3);
        }
    }
}
