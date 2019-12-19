package tech.laihz.treex.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.laihz.treex.utils.FileUtil;
import tech.laihz.treex.utils.RMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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

  /**
   * @api {get} /intro/avatar 获取头像
   * @apiName get avatar
   * @apiGroup user
   * @apiHeader {String} authorization
   * @apiSuccess {int} status
   */
  @GetMapping("/avatar")
  public void getAvatar(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String name = request.getAttribute("name").toString();
    File file = new File("FILESYSTEM"+ File.separator+"AVATAR"+File.separator+name);
    if(!file.exists()){
      RMap rMap=RMap.avatars(RMap.SUCCESS,false);
      String json = JSON.toJSONString(rMap);
      response.getWriter().write(json);
    }else {
      File targetFile = new File("FILESYSTEM"+File.separator+"AVATAR"+File.separator+name);
      String filename = targetFile.getName();
      // 取得文件的后缀名。
      String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
      InputStream fis = new BufferedInputStream(new FileInputStream(targetFile));
      byte[] buffer = new byte[fis.available()];
      fis.read(buffer);
      fis.close();
      // 清空response
      response.reset();
      response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
      response.addHeader("Content-Length", "" + targetFile.length());
      OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
      response.setContentType("application/octet-stream");
      toClient.write(buffer);
      toClient.flush();
      toClient.close();
      response.getWriter().write("CHECK");
    }

  }


  /**
   * @api {post} /intro/avatar 上传头像
   * @apiHeader {String} authorization
   * @apiParam {String} type
   * @apiParam {File} avatar
   * @apiSuccess {int} code
   * @apiGroup user
   */
  @PostMapping("/avatar")
  public RMap UploadAvatar(@RequestPart("avatar") MultipartFile file,HttpServletRequest request) throws IOException {
    String name = request.getAttribute("name").toString();
    String type = request.getParameter("type");
    File temp =File.createTempFile(name,type);
    try {
      file.transferTo(temp);
      boolean result =temp.renameTo(new File("FILESYSTEM" + File.separator + "AVATAR" + File.separator + name));
      return RMap.success(RMap.SUCCESS,"SUCCESS");
    } catch (IOException e) {
      e.printStackTrace();
      return RMap.fail(RMap.SUCCESS,"FAIL");
    }
  }

  /**
   * @api {get} /intro/space
   * @apiGroup file
   * @apiHeader {String} authorization
   *
   */
  @GetMapping("/space")
  public RMap getSpace(HttpServletRequest request) {
    String name = request.getAttribute("name").toString();
    File file = new File("FILESYSTEM" + File.separator + name);
    return RMap.space(RMap.SUCCESS, FileUtil.dirSize(file), 5 * 1024 * 1024 * 1024L);
  }



}
