import com.github.javafaker.Faker;
import ru.stellarburgers.api.UserData;

public class UserFactory {
    private final Faker faker;

    public UserFactory() {
        faker = new Faker();
    }

    public UserData createRandomUser() {
        String email = faker.internet().emailAddress();
        String password = generateValidPassword();
        String name = faker.name().firstName();
        return new UserData(email, password, name);
    }

    public UserData createUserWithInvalidPassword() {
        String email = faker.internet().emailAddress();
        String password = generateInvalidPassword();
        String name = faker.name().firstName();
        return new UserData(email, password, name);
    }

    private String generateValidPassword() {
        String password;
        do {
            password = faker.internet().password();
        } while (password.length() < 6);
        return password;
    }

    private String generateInvalidPassword() {
        return faker.internet().password(1, 5);
    }
}
