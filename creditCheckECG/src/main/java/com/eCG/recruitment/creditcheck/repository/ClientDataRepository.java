package com.eCG.recruitment.creditcheck.repository;

import com.eCG.recruitment.creditcheck.models.ClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ClientDataRepository extends JpaRepository<ClientDetails, Long> {

    @Query (value = "select * from CLIENT_DETAILS where PHONE_NO=?1 and DATE_OF_BIRTH=?2", nativeQuery = true)
    ClientDetails getClientDataByPhoneAndDob(String phoneNo, String date);
}
