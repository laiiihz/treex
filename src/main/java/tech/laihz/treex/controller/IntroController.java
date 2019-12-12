package tech.laihz.treex.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.laihz.treex.utils.RMap;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Slf4j
@RestController
@RequestMapping("/api/intro")
public class IntroController {
  @GetMapping("/auth")
  public RMap login(HttpServletRequest request) {
    String name = request.getAttribute("name").toString();
    RMap rMap = new RMap();
    rMap.put("name", name);
    return RMap.successWithExtra(200, "success connect to server", rMap);
  }

  @GetMapping("/file")
  public String fileTest() {
    RMap rMap = new RMap();
    File files = new File(".");
    return files.getAbsolutePath() + "";
    // rMap.put("name",files[0].getName());
    // return rMap;
  }


}
