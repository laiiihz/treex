package tech.laihz.treex.utils;

import java.util.HashMap;

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
      Integer code, String msg, Integer loginStatus, String name, String token) {
    RMap rMap = new RMap();
    rMap.put("status", code);
    rMap.put("message", msg);
    rMap.put("loginStatus", loginStatus);
    rMap.put("name", name);
    rMap.put("token", token);
    return rMap;
  }

  @Override
  public Object put(String key, Object value) {
    return super.put(key, value);
  }
}
