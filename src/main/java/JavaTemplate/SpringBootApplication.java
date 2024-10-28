package JavaTemplate;

import JavaTemplate.Service.SpringInitializrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication implements CommandLineRunner {
    @Autowired
    SpringInitializrService springInitializrService;
    public static void main(String[] args){
        SpringApplication.run(SpringBootApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        springInitializrService.generateProject("web,data-jpa", "my-spring-boot-app", "3.2.0");
    }
}
