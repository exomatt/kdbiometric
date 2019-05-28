package algorithms;

import javafx.util.Pair;
import utils.Triplet;
import utils.VectorFeatures;

import java.util.*;

public class QualityTests {

    public final static int euklides = 0;
    public final static int manhattan = 1;
    public final static int czebyszew = 2;


    //Returns vector predicted class
    public static String kNN(Integer k, VectorFeatures testedVector, List<VectorFeatures> vectorsToCompare, int metryka) {

        List<Pair<Double, String>> distancesList = new LinkedList<>();

        for (VectorFeatures v : vectorsToCompare) {
            switch (metryka) {
                case euklides:
                    distancesList.add(distanceEuklides(testedVector, v));
                    break;
                case manhattan:
                    distancesList.add(distanceManhattan(testedVector, v));
                    break;
                case czebyszew:
                    distancesList.add(distanceCzebyszew(testedVector, v));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + metryka);
            }
        }


        Collections.sort(distancesList, new Comparator<Pair<Double, String>>() {
            @Override
            public int compare(final Pair<Double, String> o1, final Pair<Double, String> o2) {
                if (o1.getKey() > o2.getKey()) return -1;
                else if (o1.getKey().equals(o2.getKey())) return 0;
                else return 1;
            }
        });

        //FIXME - potem usunąć :)
//        distancesList.forEach(c -> System.out.println(c.getKey() + c.getValue()));

        List<Pair<Double, String>> results = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            results.add(distancesList.get(i));
        }

        List<Triplet> klasaLicznoscSuma = new LinkedList<>();

        for (Pair<Double, String> result : results) {
            boolean czyJestDanaKlasa = false;
            Triplet triplet = null;
            for (Triplet triplet1 : klasaLicznoscSuma) {
                if (result.getValue().matches(triplet1.getKlasa())) {
                    czyJestDanaKlasa = true;
                    triplet = triplet1;
                }
            }
            if (czyJestDanaKlasa) {
                triplet.setLicznosc(triplet.getLicznosc() + 1);
                triplet.setSumaOdleglosci(triplet.getSumaOdleglosci() + result.getKey());
            } else {
                klasaLicznoscSuma.add(new Triplet(result.getValue(), 1, result.getKey()));
            }
        }

        //szukanie max licznosci
        Triplet maxLicznosc = klasaLicznoscSuma.get(0);
        for (Triplet triplet : klasaLicznoscSuma) {
            if (maxLicznosc.getLicznosc() < triplet.getLicznosc())
                maxLicznosc = triplet;
        }

        //sprawdzam czy jest tylko jedna taka
        int ilosc = 0;
        for (Triplet triplet : klasaLicznoscSuma) {
            if (triplet.getLicznosc().equals(maxLicznosc.getLicznosc())) ilosc++;
        }

        if (ilosc == 1) {
            return maxLicznosc.getKlasa();
        } else if (ilosc > 1) {
            //szukanie min odleglosci
            Triplet minDist = klasaLicznoscSuma.get(0);
            for (Triplet triplet : klasaLicznoscSuma) {
                if (minDist.getSumaOdleglosci() > triplet.getSumaOdleglosci())
                    minDist = triplet;
            }
            //sprawdzam czy jest tylko jedna taka
            int ilosc_dist = 0;
            for (Triplet triplet : klasaLicznoscSuma) {
                if (minDist.getSumaOdleglosci() == (triplet.getSumaOdleglosci())) ilosc_dist++;
            }

            if (ilosc_dist == 1) {
                return minDist.getKlasa();
            } else if (ilosc_dist > 1) {
                return minDist.getKlasa();//FIXME - to możnaby poprawić, ale mało prawdopodobne że dwa identyczne będą
            } else {

                return "COS ZLE W DYSTANSACH";
            }
        } else {
            return "COS ZLE W ILOSCI";
        }

    }

    public static Pair<Double, String> distanceEuklides(VectorFeatures vectorXFeatures, VectorFeatures
            vectorYFeatures) {
        //Pobranie list dla każdegoi wektora cech
        List<Integer> vectorX = vectorXFeatures.getVector();
        List<Integer> vectorY = vectorYFeatures.getVector();

        int poweredSum = 0;

        for (int i = 0; i < vectorX.size(); i++) {
            double pow = Math.pow(vectorX.get(i) - vectorY.get(i), 2);
            poweredSum += (int) pow;
        }
        double sqrt = Math.sqrt(poweredSum);

        return new Pair<>(sqrt, vectorYFeatures.getKlasa());
    }

    public static Pair<Double, String> distanceManhattan(VectorFeatures vectorXFeatures, VectorFeatures
            vectorYFeatures) {
        //Pobranie list dla każdegoi wektora cech
        List<Integer> vectorX = vectorXFeatures.getVector();
        List<Integer> vectorY = vectorYFeatures.getVector();

        double sum = 0;

        for (int i = 0; i < vectorX.size(); i++) {
            sum += Math.abs(vectorX.get(i) - vectorY.get(i));
        }

        return new Pair<>(sum, vectorYFeatures.getKlasa());
    }

    public static Pair<Double, String> distanceCzebyszew(VectorFeatures vectorXFeatures, VectorFeatures
            vectorYFeatures) {
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

    public static double qualityTest(List<VectorFeatures> vectorFeaturesList, int k, int metryka) {
        //TODO- wywalić potem
        vectorFeaturesList.forEach(v -> System.out.println(v.toString()));
        double wszystkie_obiekty = vectorFeaturesList.size();
        double klasyfikacje_poprawne = 0;

        for (VectorFeatures vectorFeatures : vectorFeaturesList) {
            List<VectorFeatures> copy = new LinkedList<>(vectorFeaturesList);
            copy.remove(vectorFeatures);
            String predictedClass = kNN(k, vectorFeatures, copy, metryka);
            System.out.println("predicted: " + predictedClass);
            System.out.println("powinno byc: " + vectorFeatures.getKlasa());
            System.out.println();
            if (predictedClass.matches(vectorFeatures.getKlasa())) klasyfikacje_poprawne++;
        }

        System.out.println(klasyfikacje_poprawne);
        return klasyfikacje_poprawne / wszystkie_obiekty;
    }


    public static void main(String[] args) {
        List<VectorFeatures> vectorFeaturesList = new LinkedList<>();
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("damian1"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("damian2"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("damian3"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("exo"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("exo1"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("exo3"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("fire1"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("fire2"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("fire3"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("jacek1"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("jacek2"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("jacek3"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("Jakub G"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("Jakub G1"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("Jakub G2"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("Mateusz B"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("Mateusz B1"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("Mateusz B2"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("przemek1"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("przemek2"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("przemek3"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("slawko1"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("slawko2"));
        vectorFeaturesList.add(VectorFeatures.getVectorFromFile("slawko3"));

        System.out.println(qualityTest(vectorFeaturesList, 1, manhattan));
    }

}
