package com.eCG.recruitment.creditcheck.services;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvToClientDataParser extends FileDataParser {
    private static final String DELIM_CSV = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    @Override
    public List<List<String>> parseFileDataToJson(File file) throws FileNotFoundException {
        BufferedReader reader;
        List<String> inputLines = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(
                    file));
            String line = reader.readLine();
            while (line != null) {
                inputLines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<String>> inputValuesInLines = inputLines.stream()
                .map(l -> Arrays.stream(l.split(DELIM_CSV)).collect(Collectors.toList())).collect(Collectors.toList());
        return inputValuesInLines;
    }
}
