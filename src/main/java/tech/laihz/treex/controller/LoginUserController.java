package tech.laihz.treex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import tech.laihz.treex.entity.LoginUser;
import tech.laihz.treex.service.LoginUserService;
import tech.laihz.treex.utils.JWTUtil;
import tech.laihz.treex.utils.RMap;

@RestController
@RequestMapping(value = "/api")
public class LoginUserController {
    @Autowired
    LoginUserService loginUserService;
    private final Jedis jedis = new Jedis("127.0.0.1",6379);

    @GetMapping("/login")
    public RMap login(LoginUser loginUser) {
        LoginUser loginUserStorage = loginUserService.getUserByName(loginUser.getName());
        if(loginUserStorage==null){
            return RMap.login(RMap.SUCCESS,"no user",RMap.LOGIN_NO_USER,null,null);
        }else {
            String name= loginUser.getName();
            if(loginUserStorage.getName().equals(loginUser.getName())&&loginUserStorage.getPassword().equals(loginUser.getPassword())){
                //LOGIN SUCCESS
                String token =JWTUtil.createToken(loginUserStorage);
                jedis.set(token,loginUser.getName());
                return RMap.login(RMap.SUCCESS,"login success",RMap.LOGIN_SUCCESS,name,token);
            }else {
                //PASSWORD WRONG
                return RMap.login(RMap.SUCCESS,"login success",RMap.LOGIN_PASSWORD_WRONG,name,null);
            }
        }
    }


    @PostMapping("/newuser")
    public String newUser(LoginUser loginUser){
        loginUserService.addUser(loginUser);
        return "SUCCESS";
    }

    @GetMapping("/existuser")
    public RMap existUser(LoginUser loginUser){
        LoginUser loginUserNow = loginUserService.getUserByName(loginUser.getName());
        if(loginUserNow==null){
            return RMap.existUser(RMap.SUCCESS,"don't have user",RMap.NO_USER,null);
        }else {
            return RMap.existUser(RMap.SUCCESS,"have user",RMap.HAVE_USER,loginUserNow.getName());
        }
    }
}
