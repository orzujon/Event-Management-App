package com.ab.modules;

import java.io.Serializable;

public class Event implements Serializable{
  //private static final long serialVersionUID = -80635878058194550L;

  private String evName;
  private int evID;
  private String evDate;

  public Event(int evID, String evDate, String evName){
    this.evName = evName;
    this.evID = evID;
    this.evDate = evDate;
  }
  //getters

  public String getevName(){
    return evName;
  }
  public int getevID(){
    return evID;
  }
  public String getevDate(){
    return evDate;
  }

  public String toString(){
    return evID + " " + evDate + " " + evName;
  }

}