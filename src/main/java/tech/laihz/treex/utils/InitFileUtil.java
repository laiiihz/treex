package tech.laihz.treex.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InitFileUtil {
  public InitFileUtil(String name) {
    File file = new File("FILESYSTEM" + File.separator + name);
    boolean result = file.mkdir();
  }

  private void genDir(String name, String path) {
    File file = new File(path + File.separator + name);
    boolean result = file.mkdir();
  }

  public static void globalInit() {
    File file = new File("FILESYSTEM");
    File shareDir = new File(file.getPath() + File.separator + "SHARE");
    File avatar = new File("AVATAR");
    boolean result = file.mkdir();
    boolean resultDir = shareDir.mkdir();
    boolean resultAvatar = avatar.mkdir();
  }
}
