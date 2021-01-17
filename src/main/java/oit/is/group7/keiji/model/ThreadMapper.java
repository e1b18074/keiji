package oit.is.group7.keiji.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ThreadMapper {

  /**
   * @param threadNumber
   * @return
   */
  @Select("SELECT * from THREAD WHERE THREADNUMBER = #{threadNumber}")
  Thread selectByThreadNumber(int threadNumber);

  /**
   * @param threadNumber
   * @return
   */
  @Select("SELECT THREADNUMBER from THREAD WHERE THREADNUMBER = (SELECT MAX(THREADNUMBER) FROM THREAD);")
  int selectThreadNumber();

  /**
   * @return
   */
  @Select("SELECT * from THREAD ORDER BY THREADNUMBER DESC;")
  ArrayList<Thread> selectAllThread();

  /**
   * @param thread
   */
  @Insert("INSERT INTO THREAD (user,title,date) VALUES (#{user},#{title},#{date});")
  void insertThread(Thread thread);

  /**
   * @param threadNumber
   * @return
   */
  @Delete("DELETE FROM THREAD WHERE THREADNUMBER = #{threadNumber}")
  boolean deleteByNumber(int threadNumber);

  /**
   * @return
   */
  @Delete("DELETE FROM THREAD")
  boolean deleteAllThread();

  /**
   * @param thread
   */
  @Update("UPDATE THREAD SET GOOD= #{good}+1 WHERE THREADNUMBER = #{threadNumber}")
  void updateThread(Thread Thread);

}
