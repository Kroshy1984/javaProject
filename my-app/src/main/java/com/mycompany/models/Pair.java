package com.mycompany.models;

public class Pair {
  private Long id;
  private Long Boy_id;
  private Long Girl_id;
public Long getId() {
    return id;
}
@Override
public String toString() {
    return "Pair [Boy_id=" + Boy_id + ", Girl_id=" + Girl_id + ", id=" + id + "]";
}
public void setId(Long id) {
    this.id = id;
}
public Long getBoy_id() {
    return Boy_id;
}
public void setBoy_id(Long boy_id) {
    Boy_id = boy_id;
}
public Long getGirl_id() {
    return Girl_id;
}
public void setGirl_id(Long girl_id) {
    Girl_id = girl_id;
}
      
}
