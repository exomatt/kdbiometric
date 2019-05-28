package algorithms;

import javafx.util.Pair;
import utils.VectorFeatures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QualityTests {

    public static Pair<Double, String> distanceEuklides(VectorFeatures vectorXFeatures, VectorFeatures vectorYFeatures) {
        //Pobranie list dla każdegoi wektora cech
        List<Integer> vectorX = vectorXFeatures.getVector();
        List<Integer> vectorY = vectorYFeatures.getVector();

        int poweredSum = 0;

        for (int i = 0; i < vectorX.size(); i++) {
            double pow = Math.pow(vectorX.get(i) - vectorY.get(i), 2);
            poweredSum += Integer.parseInt(Double.toString(pow));
        }
        double sqrt = Math.sqrt(poweredSum);

        return new Pair<>(sqrt, vectorYFeatures.getKlasa());
    }

    public static Pair<Double, String> distanceManhattan(VectorFeatures vectorXFeatures, VectorFeatures vectorYFeatures) {
        //Pobranie list dla każdegoi wektora cech
        List<Integer> vectorX = vectorXFeatures.getVector();
        List<Integer> vectorY = vectorYFeatures.getVector();

        double sum = 0;

        for (int i = 0; i < vectorX.size(); i++) {
            sum += Math.abs(vectorX.get(i) - vectorY.get(i));
        }

        return new Pair<>(sum, vectorYFeatures.getKlasa());
    }

    public static Pair<Double, String> distanceCzebyszew(VectorFeatures vectorXFeatures, VectorFeatures vectorYFeatures) {
        //Pobranie list dla każdegoi wektora cech
        List<Integer> vectorX = vectorXFeatures.getVector();
        List<Integer> vectorY = vectorYFeatures.getVector();

        List<Integer> absoluteDifferences = new ArrayList<>();

        for (int i = 0; i < vectorX.size(); i++) {
            absoluteDifferences.add(Math.abs(vectorX.get(i) - vectorY.get(i)));
        }

        Collections.sort(absoluteDifferences);
        Double max = Double.valueOf(absoluteDifferences.get(absoluteDifferences.size() - 1));

        return new Pair<>(max, vectorYFeatures.getKlasa());
    }

}
