package oit.is.group7.keiji.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CommentMapper {

  /**
   * @param number
   * @return
   */
  @Select("SELECT * from COMMENT WHERE NUMBER = #{number}")
  Comment selectByNumber(int number);

  /**
   * @param threadNumber
   * @return
   */
  @Select("SELECT * from COMMENT WHERE THREADNUMBER = #{threadNumber}")
  ArrayList<Comment> selectByThreadNumber(int threadNumber);


  /**
   * @return
   */
  @Select("SELECT * from COMMENT ORDER BY NUMBER DESC;")
  ArrayList<Comment> selectAllComment();

  /**
   * @return
   */
  @Select("select count(*) from COMMENT;")
  int selectCountComment();

  /**
   * @param comment
   */
  @Insert("INSERT INTO comment (user,userComment,date,threadNumber) VALUES (#{user},#{userComment},#{date},#{threadNumber});")
  void insertComment(Comment comment);

  /**
   * @param comment
   */
  @Insert("INSERT INTO comment (number,user,userComment,good,threadNumber) VALUES (#{number},#{user},#{userComment},#{date},#{good},#{threadNumber});")
  void insertCommentCopy(Comment comment);

  /**
   * @param number
   * @return
   */
  @Delete("DELETE FROM COMMENT WHERE NUMBER =#{number}")
  boolean deleteByNumber(int number);

   /**
   * @param number
   * @return
   */
  @Delete("DELETE FROM COMMENT WHERE THREADNUMBER = #{threadNumber}")
  boolean deleteByThreadNumber(int threadNumber);

  /**
   * @return
   */
  @Delete("DELETE FROM COMMENT")
  boolean deleteAllComment();

  /**
   * @param comment
   */
  @Update("UPDATE COMMENT SET GOOD= #{good}+1 WHERE NUMBER = #{number}")
  void updateComment(Comment comment);
}
