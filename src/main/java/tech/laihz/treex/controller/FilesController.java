package tech.laihz.treex.controller;

import org.springframework.web.bind.annotation.*;
import tech.laihz.treex.utils.RMap;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@RestController
@RequestMapping("api/intro")
public class FilesController {
    /**
     * @api {get} /intro/files 获取私有文件列表
     * @apiGroup files
     * @apiName get cloud private file list
     * @apiParam {String} path
     * @apiHeader {String} authorization
     */
    @GetMapping("files")
    RMap getRoot(HttpServletRequest request){
        String name = request.getAttribute("name").toString();
        String path = request.getParameter("path");
        return RMap.file(RMap.SUCCESS,"SUCCESS FETCH",name, new File("FILESYSTEM"+File.separator+name+File.separator+path));
    }

    /**
     * @api {get} /intro/share 获取共享文件列表
     * @apiGroup files
     * @apiName get cloud shared file list
     * @apiParam {String} path
     * @apiHeader {String} authorization 测试
     */
    @GetMapping("share")
    RMap getShareList(HttpServletRequest request){
        String path = request.getParameter("path");
        return RMap.file(RMap.SUCCESS,"SUCCESSFUL FETCH","share",new File("FILESYSTEM"+File.separator+"SHARE"+File.separator+path));
    }

    /**
     * @api {put} /intro/newFolder 新建文件夹
     * @apiGroup files
     * @apiName create a new folder in path
     * @apiParam {String} path
     * @apiParam {String} folder
     * @apiHeader {String} authorization
     */
    @PutMapping("newFolder")
    RMap putFolder(HttpServletRequest request){
        String name = request.getAttribute("name").toString();
        String path = request.getParameter("path");
        String folder = request.getParameter("folder");
        File createFile=new File("FILESYSTEM"+File.separator+name+File.separator+path+File.separator+folder);
        Boolean result=createFile.mkdir();
        return RMap.success(RMap.SUCCESS,"SUCCESS CREATE FOLDER");
    }

    /**
     * @api {delete} /intro/deleteFile 删除私有云文件
     * @apiGroup files
     * @apiName delete a file in path
     * @apiParam {String} path
     * @apiHeader {String} authorization
     */
    @DeleteMapping("deleteFile")
    RMap deleteFolder(HttpServletRequest request) {
        String name = request.getAttribute("name").toString();
        String path = request.getParameter("path");
        File deleteFile = new File("FILESYSTEM"+File.separator+name+File.separator+path);
        Boolean result = deleteFile.delete();
        return RMap.success(RMap.SUCCESS,"SUCCESS DELETE File");
    }

    @PutMapping("toShare")
    RMap toShare(HttpServletRequest request) {
        String name = request.getAttribute("name").toString();
        String path = request.getParameter("path");
        File origin = new File("FILESYSTEM"+File.separator+name+File.separator+path);
        File target = new File("FILESYSTEM"+File.separator+"SHARE"+File.separator+path);
        FileChannel originChannel = null;
        FileChannel targetChannel = null;
        try {
            originChannel  = new FileInputStream(origin).getChannel();
            targetChannel = new FileOutputStream(target).getChannel();
            targetChannel.transferFrom(originChannel,0,originChannel.size());
        }catch (IOException e){
            return RMap.fail(RMap.SUCCESS,e.toString());
        }finally {
            try {
                assert originChannel != null;
                originChannel.close();
                assert targetChannel != null;
                targetChannel.close();
            }catch (IOException e){
                return RMap.fail(RMap.SUCCESS,e.toString());
            }
        }
        return RMap.success(RMap.SUCCESS,"SUCCESS MOVE");
    }
}