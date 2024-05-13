package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car = new Car("Lada", 34876539);
        User user1 = new User("Иван", "Иванов", "ivanovsk@yandex.ru", car);
        userService.add(user1);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru",
                new Car("BMV", 2763427)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru",
                new Car("KIA", 00340)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru",
                new Car("Jeep", 3784653)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru",
                new Car("Nissan", 00353)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        System.out.println(car.getId());
        User userResult = userService.getCarOwner(car);
        System.out.printf("Id = %s, First Name = %s, Last Name = %s, Email = %s, Car = %s %s",
                userResult.getId(),
                userResult.getFirstName(),
                userResult.getLastName(),
                userResult.getEmail(),
                userResult.getCar().getModel(),
                userResult.getCar().getSeries());

        context.close();
    }
}
