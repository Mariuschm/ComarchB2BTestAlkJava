package pl.alk.model;

import com.opencsv.bean.CsvBindByPosition;


public class LoginDataModel {
    @CsvBindByPosition(position = 0, required = true)
    private String companyName;
    @CsvBindByPosition(position = 1, required = true)
    private String userName;
    @CsvBindByPosition(position = - 2, required = true)
    private String password;
    @CsvBindByPosition(position = 3, required = true)
    private byte acceptTerms;

    public String getCompanyName() {
        return companyName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public byte getAcceptTerms() {
        return acceptTerms;
    }


}
