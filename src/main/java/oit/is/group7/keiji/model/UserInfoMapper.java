package oit.is.group7.keiji.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;


@Mapper
public interface UserInfoMapper {

  /**
   * @return
   */
  @Select("SELECT * from userInfo WHERE name = #{name}")
  UserInfo selectByUser(String name);


  /**
   * @return
   */
  @Select("SELECT * from userInfo WHERE number = #{number}")
  UserInfo selectByNumber(int number);

  /**
   * @return
   */
  @Select("select * from userInfo;")
  ArrayList<UserInfo> selectAllUserInfo();

  /**
   * @param userInfo
   */
  @Insert("INSERT INTO userInfo (name, password, role) VALUES (#{name},#{password},#{role});")
  //@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUserInfo(UserInfo userInfo);


  /**
   * @param id
   * @return
   */
  @Delete("DELETE FROM userInfo WHERE number =#{number}")
  boolean deleteByNumber(int number);

}
