package utils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReaderUtil {

    private static final String regPattern = "\\b[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}\\b";

    public List<String> readRegNumber(String filePath){
        List<String> regNumbers = new ArrayList<>();
        Pattern pattern = Pattern.compile(regPattern);
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = br.readLine()) != null){
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()){
                    regNumbers.add(matcher.group());

                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return regNumbers;
    }

    public Map<String, String> readExpectedResults(String filePath) {
        Map<String, String> results = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    String regNumber = parts[0].trim();
                    String valuation = parts[4].trim();
                    results.put(regNumber, valuation);
                } else {
                    System.out.println("Error on line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }

}
