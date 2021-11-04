package com.nick.demo.model;

public class User {
    String name;
    int age;

    public static User of(String name, int age){
         User u=new User();
         u.name=name;
         u.age=age;
         u.initData();
         return u;
    }
    public void initData(){
        try {
            Thread.sleep((long) (1000*(Math.random()*10)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
