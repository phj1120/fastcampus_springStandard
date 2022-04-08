package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

class Car{}
class SportCar extends  Car{}
class Truck extends Car{}

class Engine {}

// ch3/config.txt
// car=com.fastcampus.ch3.diCopy1.Truck
// engine=com.fastcampus.ch3.diCopy1.Engine

public class Main1 {
    public static void main(String[] args) throws Exception {
//        Car car = getCar();
        Car car = (Car) getObject("car");
        Engine engine = (Engine) getObject("engine");

        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
    }

//    config.txt 에서 어떤 Car 를 쓸지 
    static Car getCar() throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));
        Class clazz = Class.forName(p.getProperty("car"));
        return (Car)clazz.newInstance();
    }

//    car 뿐만 아니라 다른 클래스도 사용 가능하도록 수정
    static Object getObject(String key) throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class<?> clazz = Class.forName(p.getProperty(key));
        return clazz.newInstance();
    }
}
