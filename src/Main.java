import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        double stalauczenia = 0.2;
        String plikterningowy = "trainset.csv";
        String pliktestowy = "testset.csv";

        List<Integer> trainLabels = new ArrayList<>();
        List<double[]> trainData = Data.loadData(plikterningowy, trainLabels);

        List<Integer> testLabels = new ArrayList<>();
        List<double[]> testData = Data.loadData(pliktestowy, testLabels);

        Perceptron perceptron = new Perceptron(trainData.get(0).length, stalauczenia);

        double[][] trainArray = trainData.toArray(new double[trainData.size()][]);
        int[] trainLabelsArray = trainLabels.stream().mapToInt(i -> i).toArray();
        perceptron.trening(trainArray, trainLabelsArray);

        double[][] testArray = testData.toArray(new double[testData.size()][]);
        int[] testLabelsArray = testLabels.stream().mapToInt(i -> i).toArray();
        perceptron.test(testArray, testLabelsArray);

        perceptron.klient();
    }
}
