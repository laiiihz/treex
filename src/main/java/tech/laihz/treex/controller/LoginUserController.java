package tech.laihz.treex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import tech.laihz.treex.entity.LoginUser;
import tech.laihz.treex.service.LoginUserService;
import tech.laihz.treex.utils.InitFileUtil;
import tech.laihz.treex.utils.JWTUtil;
import tech.laihz.treex.utils.RMap;

@RestController
@RequestMapping(value = "/api")
public class LoginUserController {
  @Autowired LoginUserService loginUserService;
  private final Jedis jedis = new Jedis("127.0.0.1", 6379);

  /**
   * @api {delete} /delete/:token 删除token接口
   * @apiName delete token from redis
   * @apiGroup auth
   * @apiParam {String} token
   * @apiSuccess {int} status
   * @apiSuccess {String} message
   */
  @DeleteMapping("/delete/{token}")
  public RMap deleteToken(@PathVariable String token) {
    jedis.del(token);
    return RMap.success(RMap.SUCCESS, "delete success");
  }


  /**
   * @api {get} /login 登录接口
   * @apiName login
   * @apiParam {String} name
   * @apiParam {String} password
   * @apiGroup auth
   * @apiSuccess {String} token
   * @apiSuccess {int} status
   * @apiSuccess {String} message
   * @apiSuccess {int} loginStatus
   */
  @GetMapping("/login")
  public RMap login(LoginUser loginUser) {
    LoginUser loginUserStorage = loginUserService.getUserByName(loginUser.getName());
    if (loginUserStorage == null) {
      // NO USER
      return RMap.login(RMap.SUCCESS, "no user", RMap.LOGIN_NO_USER, null, null);
    } else {
      String name = loginUser.getName();
      if (loginUserStorage.getName().equals(loginUser.getName())
          && loginUserStorage.getPassword().equals(loginUser.getPassword())) {
        // LOGIN SUCCESS
        String token = JWTUtil.createToken(loginUserStorage);
        jedis.setex(token, 100,loginUser.getName());
        return RMap.login(RMap.SUCCESS, "login success", RMap.LOGIN_SUCCESS, loginUserStorage, token);
      } else {
        // PASSWORD WRONG
        return RMap.login(RMap.SUCCESS, "password wrong", RMap.LOGIN_PASSWORD_WRONG, null, null);
      }
    }
  }

  /**
   * @api {put} /newuser 新建用户接口
   * @apiGroup auth
   * @apiName create a account
   * @apiParam {String} name
   * @apiParam {String} password
   * @apiSuccess {int} status
   * @apiSuccess {String} message
   */
  @PutMapping("/newuser")
  public RMap newUser(LoginUser loginUser) {
    loginUserService.addUser(loginUser);
    new InitFileUtil(loginUser.getName());
    return RMap.success(RMap.SUCCESS, "create dir success");
  }

  /**
   * @api {get} /existuser 检查用户存在性
   * @apiName check account exist
   * @apiGroup auth
   * @apiParam {String} name
   * @apiSuccess {int} status
   * @apiSuccess {String} message
   */
  @GetMapping("/existuser")
  public RMap existUser(LoginUser loginUser) {
    LoginUser loginUserNow = loginUserService.getUserByName(loginUser.getName());
    if (loginUserNow == null) {
      return RMap.existUser(RMap.SUCCESS, "don't have user", RMap.NO_USER, null);
    } else {
      return RMap.existUser(RMap.SUCCESS, "have user", RMap.HAVE_USER, loginUserNow.getName());
    }
  }
}
