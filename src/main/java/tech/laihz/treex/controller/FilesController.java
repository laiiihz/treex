package tech.laihz.treex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.laihz.treex.utils.RMap;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("api/intro")
public class FilesController {
    @GetMapping("root")
    RMap getRoot(HttpServletRequest request){
        String name = request.getAttribute("name").toString();
        String path = request.getParameter("path");
        return RMap.file(RMap.SUCCESS,name, new File("FILESYSTEM"+File.separator+name+File.separator+path));
    }
    @GetMapping("roots")
    RMap getFile(HttpServletRequest request){
        String name = request.getAttribute("name").toString();
        String path = request.getParameter("path");
        return RMap.success(RMap.SUCCESS,path);
    }
}
