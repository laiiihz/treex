package tech.laihz.treex.utils;

import tech.laihz.treex.entity.LoginUser;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class FileUtil {
  private String name;

  public FileUtil(String name) {
    this.name = name;
  }

  public static long dirSize(File file){
    return getSize(file);
  }

  private static long getSize(File initFile){
    long result =0;
    if(initFile!=null&&initFile.isDirectory()){
      for (File file: Objects.requireNonNull(initFile.listFiles())){
        if(file.isDirectory()){
          result = result + getSize(file);
        }else {
          result += file.length();
        }
      }
    }
    return result;
  }

  //  public List<File> list(){
  //      File file = new File("FILESYSTEM"+File.separator+"");
  //  }
}
