package pl.edu.amu.wmi.students.mario.isi.searchengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * Created by Mariusz on 2017-05-29.
 */
@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = DataLoader.class))
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
