package tech.laihz.treex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.laihz.treex.entity.LoginUser;
import tech.laihz.treex.mapper.LoginUserMapper;


@Service
public class LoginUserService {
    @Autowired
    LoginUserMapper loginUserMapper;

    public int addUser(LoginUser loginUser){
        return loginUserMapper.addUser(loginUser);
    }
    public LoginUser getUserByName(String name){
        return loginUserMapper.getUserByName(name);
    }


}
