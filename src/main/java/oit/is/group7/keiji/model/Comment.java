package oit.is.group7.keiji.model;

public class Comment {
  int number;
  String user;
  String userComment;
  int good;

  /**
   * 引数ありコンストラクタを作る際，Beanであれば必ずデフォルトコンストラクタを明示的に追加すること
   */
  public Comment() {

  }

  public Comment(String user, String userComment){
    this.userComment = userComment;
    this.user = user;
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
