package tech.laihz.treex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.laihz.treex.ResponseBo;
import tech.laihz.treex.mapper.LoginUserMapper;
import tech.laihz.treex.service.LoginUserService;
import tech.laihz.treex.utils.RMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
  @Autowired
  LoginUserService loginUserService;

  @RequestMapping(value = "/api/test", method = RequestMethod.GET)
  public RMap Test() {
    return RMap.success(RMap.SUCCESS, "success");
  }

  @GetMapping(value = "/api/check-connection")
  public RMap CheckConnection() {
    return RMap.success(RMap.SUCCESS, "success");
  }

  @GetMapping("/download")
  public void TestDownload(HttpServletResponse response) throws IOException {
    File targetFile = new File("./FILESYSTEM/1.jpg");
    String filename = targetFile.getName();
    // 取得文件的后缀名。
    String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
    InputStream fis = new BufferedInputStream(new FileInputStream(targetFile));
    byte[] buffer = new byte[fis.available()];
    fis.read(buffer);
    fis.close();
    // 清空response
    response.reset();
    // 设置response的Header
    response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
    response.addHeader("Content-Length", "" + targetFile.length());
    OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
    response.setContentType("application/octet-stream");
    toClient.write(buffer);
    toClient.flush();
    toClient.close();

  }

  @PostMapping("/upload")
  public RMap Upload(@RequestPart("image") MultipartFile file) {
    return RMap.success(RMap.SUCCESS,file.getSize()+"");
  }

  @PutMapping("/set-phone")
  public RMap setPhone(@RequestParam String name, @RequestParam String phone) {
    loginUserService.setUserPhone(name,phone);
    return RMap.success(RMap.SUCCESS, "OK");
  }
}
