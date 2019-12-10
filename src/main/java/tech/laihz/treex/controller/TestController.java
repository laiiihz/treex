package tech.laihz.treex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.laihz.treex.ResponseBo;
import tech.laihz.treex.utils.RMap;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
  @RequestMapping(value = "/api/test", method = RequestMethod.GET)
  public RMap Test() {
    return RMap.success(RMap.SUCCESS,"success");
  }

  @GetMapping(value = "/api/check-connection")
  public RMap CheckConnection() {
    return RMap.success(RMap.SUCCESS,"success");
  }
}
