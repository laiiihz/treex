package tech.laihz.treex.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.laihz.treex.utils.RMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

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

  @GetMapping("/avatar")
  public void getAvatar(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String name = request.getAttribute("name").toString();
    File file = new File("FILESYSTEM"+ File.separator+"AVATAR"+File.separator+name);
    if(!file.exists()){
      RMap rMap=RMap.avatars(RMap.SUCCESS,false);
      String json = JSON.toJSONString(rMap);
      response.getWriter().write(json);
    }else {
      response.getWriter().write("CHECK");
    }

  }

  @PostMapping("/avatar")
  public RMap UploadAvatar(@RequestPart("avatar") MultipartFile file,HttpServletRequest request) throws IOException {
    String name = request.getAttribute("name").toString();
    String type = request.getParameter("type");
    File temp =File.createTempFile(name,type);
    try {
      file.transferTo(temp);
      boolean result =temp.renameTo(new File("FILESYSTEM" + File.separator + "AVATAR" + File.separator + name + "." + type));
      return RMap.success(RMap.SUCCESS,"SUCCESS");
    } catch (IOException e) {
      e.printStackTrace();
      return RMap.fail(RMap.SUCCESS,"FAIL");

    }
  }



}
