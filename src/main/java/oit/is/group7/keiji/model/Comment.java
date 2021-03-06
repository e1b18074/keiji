package oit.is.group7.keiji.model;

public class Comment {
  int number;
  String user;
  String userComment;
  String date;
  int threadNumber;
  int good;

  /**
   * 引数ありコンストラクタを作る際，Beanであれば必ずデフォルトコンストラクタを明示的に追加すること
   */
  public Comment() {

  }

  public Comment(String user, String userComment, String date, int threadNumber){
    this.userComment = userComment;
    this.user = user;
    this.date = date;
    this.threadNumber = threadNumber;
  }

  public String getUserComment(){
    return userComment;
  }

  public void setUserComment(String userComment){
    this.userComment = userComment;
  }

  public String getUser(){
    return user;
  }

  public  void setUser(String user){
    this.user = user;
  }

  public String getDate(){
    return date;
  }

  public  void setDate(String date){
    this.date = date;
  }

  public int getThreadNumber(){
    return threadNumber;
  }

  public void setThreadNumber(int threadNumber){
    this.threadNumber = threadNumber;
  }

  public int getNumber(){
    return number;
  }

  public void setNumber(int number){
    this.number = number;
  }

  public int getGood(){
    return good;
  }

  public void setGood(int good){
    this.good = good;
  }
}
