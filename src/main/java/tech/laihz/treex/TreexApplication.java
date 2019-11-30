package tech.laihz.treex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "tech.laihz.treex.filter")
public class TreexApplication {
    public static void main(String[] args) {
        SpringApplication.run(TreexApplication.class, args);
    }

}
