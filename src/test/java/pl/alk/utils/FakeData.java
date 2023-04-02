package pl.alk.utils;

import com.github.javafaker.Faker;
import java.util.Locale;

/**
 * Class with fake data
 */
public class FakeData {
    private String companyName;
    private String userName;
    private String password;

    public FakeData(){
        var faker = new Faker(new Locale("pl-PL"));
        companyName = faker.company().name();
        userName=faker.name().username();
        password=faker.internet().password(4,8);
    }


}
