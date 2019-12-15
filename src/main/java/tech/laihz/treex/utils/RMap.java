package tech.laihz.treex.utils;

import tech.laihz.treex.entity.LoginUser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RMap extends HashMap<String, Object> {
  public static final Integer SUCCESS = 200;
  public static final Integer FAIL = 500;
  public static final Integer WARN = 1;
  public static final Integer NO_PERMISSION = 3001;

  public static RMap success(Integer code, String msg) {
    RMap rMap = new RMap();
    rMap.put("status", code);
    rMap.put("message", msg);
    return rMap;
  }

  public static RMap successWithExtra(Integer code, String msg, Object data) {
    RMap rMap = new RMap();
    rMap.put("status", code);
    rMap.put("message", msg);
    rMap.put("data", data);
    return rMap;
  }

  public static RMap fail(Integer code, String msg) {
    RMap rMap = new RMap();
    rMap.put("status", code);
    rMap.put("message", msg);
    return rMap;
  }

  public static final boolean HAVE_USER = true;
  public static final boolean NO_USER = false;

  public static RMap existUser(Integer code, String msg, boolean exist, String name) {
    RMap rMap = new RMap();
    rMap.put("status", code);
    rMap.put("message", msg);
    rMap.put("exist", exist);
    rMap.put("name", name);
    return rMap;
  }

  public static final Integer LOGIN_NO_USER = 2001;
  public static final Integer LOGIN_SUCCESS = 2000;
  public static final Integer LOGIN_PASSWORD_WRONG = 2002;

  public static RMap login(
          Integer code, String msg, Integer loginStatus, LoginUser loginUser, String token) {
    RMap rMap = new RMap();
    rMap.put("status", code);
    rMap.put("message", msg);
    rMap.put("loginStatus", loginStatus);
    rMap.put("user", loginUser);
    rMap.put("token", token);
    return rMap;
  }
  public static List<RMap> singleFile(File[] files){
    List<RMap> rMaps = new ArrayList<RMap>();
    for(File single : files){
      RMap rMap = new RMap();
      rMap.put("name",single.getName());
      rMap.put("isDir",single.isDirectory());
      rMap.put("length",single.length());
      rMap.put("date",single.lastModified());
      rMap.put("subLength",single.listFiles()==null?0: Objects.requireNonNull(single.listFiles()).length);
      rMaps.add(rMap);
    }
    return rMaps;
  }

  public static RMap file(Integer code, String msg,String name, File file){
    RMap rMap = new RMap();
    rMap.put("status", code);
    rMap.put("message",msg);
    rMap.put("name",name);
    rMap.put("file",RMap.singleFile(Objects.requireNonNull(file.listFiles())));
    return rMap;
  }
  public static RMap avatars(Integer code ,boolean haveAvatar){
    RMap rMap = new RMap();
    rMap.put("status", code);
    rMap.put("haveAvatar",haveAvatar);
    return rMap;
  }

  @Override
  public Object put(String key, Object value) {
    return super.put(key, value);
  }
}
