package com.eCG.recruitment.creditcheck.services;

import com.eCG.recruitment.creditcheck.config.Constants;
import com.eCG.recruitment.creditcheck.exception.FileParsingException;
import com.eCG.recruitment.creditcheck.models.ClientDetails;
import com.eCG.recruitment.creditcheck.utils.Utility;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public abstract class FileDataParser {
    public abstract List<List<String>> parseFileDataToJson(File file) throws IOException;

    public List<ClientDetails> saveClientData(List<List<String>> clientData) throws ParseException {
        //Validate on headers
        ArrayList<ClientDetails> clientDetailsList = new ArrayList<>();

        try {
            if (validateHeader(clientData.get(0))) {
                for (int i = 1; i < clientData.size(); i++) {
                    ClientDetails clientDetails = new ClientDetails();
                    List<String> data = clientData.get(i);
                    String name = Utility.removeExtraSpecialChar(data.get(0));
                    String phoneNo = Utility.removeExtraSpecialChar(data.get(3));
                    clientDetails.setLastName(name.substring(0, name.lastIndexOf(',')));
                    clientDetails.setFirstName(name.substring(name.lastIndexOf(",") + 1).trim());
                    clientDetails.setAddress(data.get(1).trim());
                    clientDetails.setPostcode(data.get(2).trim());
                    clientDetails.setPhoneNo(phoneNo);
                    clientDetails.setCreditLimit(Double.parseDouble(data.get(4)));
                    clientDetails.setDateOfBirth(Utility.processDOBData(data.get(5)));
                    clientDetailsList.add(clientDetails);
                }
            }
        }
        catch (Exception ex)
        {
            throw new FileParsingException();
        }
        return clientDetailsList;
    }

    private Boolean validateHeader(List<String> clientData) {

        if(!Utility.isNullorEmpty(clientData.get(0))&& clientData.get(0).equalsIgnoreCase(Constants.NAME)
        && !Utility.isNullorEmpty(clientData.get(3))&& clientData.get(3).equalsIgnoreCase(Constants.PHONE_NO)
        && !Utility.isNullorEmpty(clientData.get(4))&& clientData.get(5).equalsIgnoreCase(Constants.DOB))
            return true;
        return false;
    }
}
