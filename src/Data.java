import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<double[]> loadData(String fileName, List<Integer> labels) throws IOException {
        List<double[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = br.readLine()) != null) {
            String[] split = line.split(",");
            double[] wektorki = new double[split.length-1];
            for (int i = 0; i < split.length-1; i++) {
                wektorki[i] = Double.parseDouble(split[i]);
            }
            data.add(wektorki);
            String last = split[split.length-1];
            if (last.equals("Iris-setosa")){
                labels.add(1);
            } else if (last.equals("Iris-versicolor")){
                labels.add(-1);
            }
        }
        br.close();
        return data;
    }
}
