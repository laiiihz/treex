package tech.laihz.treex.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tech.laihz.treex.entity.LoginUser;

import java.util.List;

@Mapper
@Repository
public interface LoginUserMapper {
  int addUser(LoginUser user);
  int setUserPhone(String name,String phone);

  LoginUser getUserByName(String name);
}
