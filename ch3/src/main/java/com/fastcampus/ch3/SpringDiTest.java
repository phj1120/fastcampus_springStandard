package com.fastcampus.ch3;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자 꼭 만들어 주자
@Component
class Car {
    @Value("red") String color;
    @Value("100") int oil;
//    @Autowired
//    @Qualifier("superEngine")
    @Resource(name = "superEngine")
    Engine engine; // byType
    @Autowired Door[] doors;
}

@Component("engine") class Engine {} //<bean id="engine" class="com.fastcampus.ch3.Engine"/>
@Component class SuperEngine extends Engine{}
@Component class TurboEngine extends Engine{}
@Component class Door {}

@Component
public class SpringDiTest {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("config.xml");

        // ac.getBean("car").var == ctrl + alt + v
        // shift + tab : Class 변경 및 다운 캐스팅
        Car car = (Car) ac.getBean("car"); // byName
//        Car car2 = ac.getBean(Car.class); // byType
//        Car car3 = ac.getBean("car", Car.class);

//        Engine engine = (Engine) ac.getBean("superEngine");
//        Engine engine = ac.getBean(Engine.class);
//        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean("door");

//        config.xml 에서 property 태그로 설정 해 줌(setter 필요)
//        car.setColor("red");
//        car.setOil(100);
//        car.setEngine(engine);
//        car.setDoors(new Door[]{ ac.getBean(Door.class), ac.getBean(Door.class) });
//        setter 대신 @Autowired, @Value 이용

        System.out.println("car = " + car);
//        System.out.println("engine = " + engine);
    }
}
