package levels;
import utils.LoadSave;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Level {


    public static List<List<String>> importCsv() {

        List<List<String>> level_0_csv = new ArrayList<>();

        try {
            BufferedReader buffRead = new BufferedReader(new FileReader("D:/Kiwi/Coding/jumper_reloaded/jumper_reloaded/jumper_reloaded/resources/level_data/0/test_ground.csv"));
            String line;
            while ((line = buffRead.readLine()) != null) {
                String[] values = line.split(",");
                level_0_csv.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return level_0_csv;

    }
}
