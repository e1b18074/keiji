package oit.is.group7.keiji.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {

  @Select("SELECT * from comment")
  ArrayList<Comment> selectAllByUser(String user);

  /**
   * @return
   */
  @Select("select * from COMMENT;")
  ArrayList<Comment> selectAllComment();

  /**
   * @param comment
   */
  @Insert("INSERT INTO comment (user,userComment) VALUES (#{user},#{userComment});")
  @Options(useGeneratedKeys = true, keyColumn = "number", keyProperty = "number")
  void insertComment(Comment comment);

}
