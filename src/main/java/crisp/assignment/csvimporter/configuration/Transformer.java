package crisp.assignment.csvimporter.configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Transformer {

    private static Transformer instance;

    public static Transformer getInstance(String configPath) {
        return (instance == null) ? instance = new Transformer(configPath) : instance;
    }

    private Transformer(String configPath) {
        try {
            Config.loadConfig(configPath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void load(String csvPath) {
        if (Config.getString("csv.separator") == null) {
            System.out.println("Config is not loaded");
            return;
        }
        String line = "";
        String splitBy = Config.getString("csv.separator");
        boolean hasHeader = ("yes".equals(Config.getString("csv.header"))) ? true : false;
        InsertData insertData = new InsertData();
        int successLineNumber = 0;
        int failedLineNumber = 0;
        synchronized (this) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(csvPath));
                while ((line = br.readLine()) != null) {
                    if (hasHeader) {
                        hasHeader = false;
                        continue;
                    }
                    String[] columns = line.split(splitBy);
                    try {
                        insertData.insert(columns);
                        successLineNumber++;
                    } catch (Exception ex) {
                        System.out.println("#INVALID_ROW: " + Arrays.toString(columns) + " => " + ex.getMessage());
                        failedLineNumber++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(successLineNumber + " line successfully read");
            System.out.println(failedLineNumber + " line invalid");
        }
    }

}
