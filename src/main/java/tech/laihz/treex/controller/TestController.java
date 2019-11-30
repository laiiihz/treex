package tech.laihz.treex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.laihz.treex.ResponseBo;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ResponseBo Test(){
        List<ResponseBo> list =new ArrayList<ResponseBo>();
        list.add(ResponseBo.error(500,""));
        return ResponseBo.list(200,"fawefawef",100,list);
    }
}
