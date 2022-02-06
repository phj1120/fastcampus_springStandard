package com.fastcampus.ch3.diCopy4;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Component class Car{
//    Engine engine;
//    Door door;

    @Autowired Engine engine;
    @Autowired Door door;

//    @Resource Engine engine;
//    @Resource Door door;
    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}

@Component class SportCar extends  Car{}
@Component class Truck extends Car{}
@Component class Engine {}
@Component class Door { }

class AppContext{
    Map map;
    AppContext() {
        map = new HashMap();
        doComponentScan();
        doAutowired();
        doResource();
    }

    private void doResource() {
//        map에 저장된 객체의 iv 중 에 @Resource 가 붙어 있으면
//        map에서 iv의 타입에 맞는 객체를 찾아서 연결(객체의 주소를 iv 저장)
        try {
            for (Object bean : map.values()) {
                for (Field fld : bean.getClass().getDeclaredFields()) {
                    if (fld.getAnnotation(Resource.class) != null) {
                        fld.set(bean, getBean(fld.getName())); // byName
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAutowired() {
//        map에 저장된 객체의 iv 중 에 @Autowired 가 붙어 있으면
//        map에서 iv의 타입에 맞는 객체를 찾아서 연결(객체의 주소를 iv 저장)
        try {
            for (Object bean : map.values()) {
                for (Field fld : bean.getClass().getDeclaredFields()) {
                    if (fld.getAnnotation(Autowired.class) != null) {
                        fld.set(bean, getBean(fld.getType()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doComponentScan() {
//        패키지 내의 클래스 목록 가져옴
//        반복문으로 클래스를 하나씩 읽어와서 @Component 이 붙어 있는지 확인
//        @Component 가 붙어 있으면 객체를 생성해서 map 에 저장
        try {
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);

            ImmutableSet<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");
            for (ClassPath.ClassInfo classInfo : set) {
                Class clazz = classInfo.load();
                Component component = (Component) clazz.getAnnotation(Component.class);
                if(component != null){
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());
                    map.put(id, clazz.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key) throws Exception{ // byName
        return map.get(key);
    }

    Object getBean(Class clazz) { // byClass
        for (Object obj : map.values()) {
            if (clazz.isInstance(obj)) {
                return obj;
            }
        }
        return null;
    }

}

public class Main4 {
    public static void main(String[] args) throws Exception {
        AppContext ac = new AppContext();

        Car car = (Car) ac.getBean("car"); //byName
        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean(Door.class); // byClass

//        수동으로 객체 연결
//        car.engine = engine;
//        car.door = door;

        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);
    }
}
