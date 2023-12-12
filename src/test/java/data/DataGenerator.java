package data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.experimental.UtilityClass;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DataGenerator {

    private DataGenerator() {
    }

    /*Locale locale = new Locale("ru");
    Faker faker = new Faker(locale);*/

    @Value
    public static class UserInfo {
        String city;
        //String date;
        String name;
        String phone;
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.address().city();
    }

    public static String generateDate(int addDays) {
        String date = LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().firstName();
        String lastName = faker.name().lastName();
        return lastName + " " + name;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }
        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
            return user;
        }
    }




}
