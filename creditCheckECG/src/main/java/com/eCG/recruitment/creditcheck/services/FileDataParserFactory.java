package com.eCG.recruitment.creditcheck.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FileDataParserFactory {
    Logger logger= LoggerFactory.getLogger(FileDataParserFactory.class);

    @Autowired
    PrnToClientDataParser prnToClientDataParser;
    @Autowired
    CsvToClientDataParser csvToClientDataParser;

    public FileDataParser getParserByExtension(String extension) {
        switch (extension) {
            case "prn":
                return prnToClientDataParser;
            case "csv":
                return csvToClientDataParser;
            default:
                logger.info("invalid file extension");
                return null;
        }
    }
}
