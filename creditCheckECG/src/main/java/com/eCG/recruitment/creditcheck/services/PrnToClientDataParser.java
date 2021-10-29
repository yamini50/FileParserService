package com.eCG.recruitment.creditcheck.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrnToClientDataParser extends FileDataParser {
    private static final String DELIM_PRN = " ";

    @Override
    public List<List<String>> parseFileDataToJson(File file) throws IOException {
        List<List<String>> fileDataList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder sb;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                }
                sb = new StringBuilder();
                // Method called with supplied file data line and the widths of
                // each column as outlined within the file.
                String[] parts = splitStringToChunks(line, 16, 22, 9, 14, 13, 8);
                fileDataList.add(Arrays.asList(parts));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return fileDataList;
    }

    public static String[] splitStringToChunks(String inputString, int... chunkSizes) {
        List<String> list = new ArrayList<>();
        int chunkStart = 0, chunkEnd = 0;
        for (int length : chunkSizes) {
            chunkStart = chunkEnd;
            chunkEnd = chunkStart + length;
            String dataChunk = inputString.substring(chunkStart, chunkEnd);
            list.add(dataChunk.trim());
        }
        return list.toArray(new String[0]);
    }
}
