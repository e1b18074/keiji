package oit.is.group7.keiji.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;


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
  @Select("SELECT * from userInfo WHERE role = #{role}")
  ArrayList<UserInfo> selectByRole(String role);

  /**
   * @return
   */
  @Select("select * from userInfo;")
  ArrayList<UserInfo> selectAllUserInfo();

  /**
   * @param userInfo
   */
  @Insert("INSERT INTO userInfo (name, password, date, role) VALUES (#{name},#{password},#{date},#{role});")
  void insertUserInfo(UserInfo userInfo);

  /**
   * @param userInfo
   */
  @Update("UPDATE USERINFO SET NAME = #{name} WHERE NUMBER = #{number}")
  void updateName(UserInfo userInfo);

  /**
   * @param userInfo
   */
  @Update("UPDATE USERINFO SET DATE = #{date} WHERE NAME = #{name}")
  void updateLoginDate(UserInfo userInfo);

  /**
   * @param number
   * @return
   */
  @Delete("DELETE FROM userInfo WHERE number =#{number}")
  boolean deleteByNumber(int number);

}
