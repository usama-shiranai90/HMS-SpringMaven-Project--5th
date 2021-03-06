package Java.HMS.Data.Services;


import Java.HMS.Data.BloodBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BloodBankServices {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<BloodBank> findAll() {
        return jdbcTemplate.query(
                "select Bloodserialno,BloodType,DonationDate,PatientID from bloodbank;",
                (rs, rowNum) -> new BloodBank(  rs.getInt("Bloodserialno"),
                        rs.getString("BloodType")  ,rs.getDate("DonationDate").toLocalDate(),
                        rs.getInt("PatientID")));
    }

}
