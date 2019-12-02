package tech.laihz.treex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import tech.laihz.treex.utils.InitFileUtil;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@ServletComponentScan(basePackages = "tech.laihz.treex.filter")
public class TreexApplication {
  public static void main(String[] args) {
    InitFileUtil.globalInit();
    SpringApplication.run(TreexApplication.class, args);
  }
}
