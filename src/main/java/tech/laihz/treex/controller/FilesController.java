package tech.laihz.treex.controller;

import org.springframework.web.bind.annotation.*;
import tech.laihz.treex.utils.RMap;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("api/intro")
public class FilesController {
    @GetMapping("files")
    RMap getRoot(HttpServletRequest request){
        String name = request.getAttribute("name").toString();
        String path = request.getParameter("path");
        return RMap.file(RMap.SUCCESS,"SUCCESS FETCH",name, new File("FILESYSTEM"+File.separator+name+File.separator+path));
    }

    @GetMapping("share")
    RMap getShareList(HttpServletRequest request){
        String path = request.getParameter("path");
        return RMap.file(RMap.SUCCESS,"SUCCESSFUL FETCH","share",new File("FILESYSTEM"+File.separator+"SHARE"+File.separator+path));
    }

    @PutMapping("newFolder")
    RMap putFolder(HttpServletRequest request){
        String name = request.getAttribute("name").toString();
        String path = request.getParameter("path");
        String folder = request.getParameter("folder");
        File createFile=new File("FILESYSTEM"+File.separator+name+File.separator+path+File.separator+folder);
        Boolean result=createFile.mkdir();
        return RMap.success(RMap.SUCCESS,"SUCCESS CREATE FOLDER");
    }

    @DeleteMapping("deleteFile")
    RMap deleteFolder(HttpServletRequest request) {
        String name = request.getAttribute("name").toString();
        String path = request.getParameter("path");
        File deleteFile = new File("FILESYSTEM"+File.separator+name+File.separator+path);
        Boolean result = deleteFile.delete();
        return RMap.success(RMap.SUCCESS,"SUCCESS DELETE File");
    }
}