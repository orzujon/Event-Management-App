package com.ab.modules;

import java.io.Serializable;

public class Attendees implements Serializable{

  private String attName;
  private String attEvent;

  public Attendees(String attName, String attEvent){
    this.attName = attName;
    this.attEvent = attEvent;
  }

  public String getAttName(){
    return attName;
  }

  public String getAttEvent(){
    return attEvent;
  }

  public String toString(){
    return attEvent + " " + attName;
  }
}