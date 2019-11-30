package tech.laihz.treex.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class IntroController {
    @RequestMapping("/intro/test")
    public String login(HttpServletRequest request) {
        String name = request.getAttribute("name").toString();
        return "当前用户信息 name=" + name;
    }
}
