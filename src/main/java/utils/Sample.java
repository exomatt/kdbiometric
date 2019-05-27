package utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sample {
    private String letter;
    private long time;

    @Override
    public String toString() {
        return letter + ';' + time;
    }
}
