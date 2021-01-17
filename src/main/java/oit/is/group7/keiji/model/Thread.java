package oit.is.group7.keiji.model;

public class Thread {
  int threadNumber;
  String user;
  String title;
  String date;
  int good;

  public Thread() {

  }

  public Thread(String user, String title, String date){
    this.title = title;
    this.user = user;
    this.date = date;
  }

  public String getTitle(){
    return title;
  }

  public void setTitle(String title){
    this.title = title;
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

  public int getGood(){
    return good;
  }

  public void setGood(int good){
    this.good = good;
  }
}
