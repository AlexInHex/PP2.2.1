package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Model1", "Seria1");
      Car car2 = new Car("Model2", "Seria2");
      Car car3 = new Car("Model3", "Seria3");

      userService.addCar(car1);
      userService.addCar(car2);
      userService.addCar(car3);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(car1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(car2);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());

         if (user.getCar() != null) {
            System.out.println("Car Model = " + user.getCar().getModel());
            System.out.println("Car Seria = " + user.getCar().getSeria());
         }

         System.out.println();
      }

      context.close();
   }
}
