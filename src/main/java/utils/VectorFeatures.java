package utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Log
public class VectorFeatures {
    String klasa;
    List<Integer> vector;

    public static VectorFeatures getVectorFromFile(String fileName) {
        List<String> strings = null;
        try {
            strings = Files.readAllLines(Paths.get(FileSampleOperations.DB + fileName + ".txt"));
        } catch (IOException e) {
            log.severe("Problem with file...");
            e.printStackTrace();
        }
        String klasa = strings.get(0).substring(0, strings.get(0).length() - 1);
        strings.remove(0);

        List<Integer> vector = new ArrayList<>();

        for (String string : strings) {
            String[] split = string.split(";");
            vector.add(Integer.valueOf(split[1]));
        }

        return new VectorFeatures(klasa, vector);
    }

//    public static void main(String[] args) {
//        VectorFeatures przemek1 = getVectorFromFile("przemek1");
//        System.out.println(przemek1.getKlasa());
//        przemek1.getVector().forEach(s -> System.out.println(s.toString()));
//    }
}
