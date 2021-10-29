package com.eCG.recruitment.creditcheck;

import com.eCG.recruitment.creditcheck.exception.SaveClientDataException;
import com.eCG.recruitment.creditcheck.repository.ClientDataRepository;
import com.eCG.recruitment.creditcheck.services.ClientDataService;
import com.eCG.recruitment.creditcheck.services.FileDataParser;
import com.eCG.recruitment.creditcheck.services.FileDataParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoadDataDriver {
    private final FileDataParserFactory fileDataParserFactory;
    private static final Logger logger = LoggerFactory.getLogger(LoadDataDriver.class);
    @Autowired
    ClientDataService clientDataService;

    @Autowired
    public LoadDataDriver(FileDataParserFactory fileDataParserFactory) {
        this.fileDataParserFactory = fileDataParserFactory;
    }
    @Value("${upload-folder-location}")
    private String folderLocation;

    public void loadDataDriver() {
        logger.info("fetch all files from upload folder");
        //read files from upload folder and process it
        List<File> filesInFolder = null;
        try {
            filesInFolder = Files.walk(Paths.get(folderLocation))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException exception) {
            logger.error("Failed to get all files from folder, reason: " + exception.getMessage());
        }
        filesInFolder.stream().forEach(s -> logger.info(s.getName()));
        filesInFolder.stream().forEach(f ->
        {
            String extension = f.getName().substring(f.getName().lastIndexOf(".") + 1);
            FileDataParser parser = fileDataParserFactory.getParserByExtension(extension);
            List<List<String>> clientData = new ArrayList<>();
            try {
                clientData.addAll(parser.parseFileDataToJson(f));
            } catch (IOException exception) {
                throw new SaveClientDataException();
            }
            try {
                clientDataService.saveClientData(parser.saveClientData(clientData));
            } catch (ParseException e) {
                throw new SaveClientDataException();
            }
        });
    }
}