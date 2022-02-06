package com.fastcampus.ch3;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 꼭 만들어 주자
class Car {
    String color;
    int oil;
    Engine engine;
    Door[] doors;
}

class Engine {}
class Door {}

public class SpringDiTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");

        // ac.getBean("car").var == ctrl + alt + v
        // shift + tab : Class 변경 및 다운 캐스팅
        Car car = (Car) ac.getBean("car"); // byName
        Car car2 = ac.getBean(Car.class); // byType
//        Car car3 = ac.getBean("car", Car.class);

        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean("door");

//        config.xml 에서 property 태그로 설정 해 줌(setter 필요)
        car.setColor("red");
        car.setOil(100);
        car.setEngine(engine);
        car.setDoors(new Door[]{ ac.getBean(Door.class), ac.getBean(Door.class) });

        System.out.println("car = " + car);
        System.out.println("car2 = " + car2);
    }
}
