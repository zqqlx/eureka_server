package net.xdclass.eureka_server.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName Student
 * @Description TODO
 * @Author Administrator
 * @dATE 2019/6/30 9:05
 * @Version 1.0
 **/
@Data
public class Student implements Serializable {
    private Integer id;

    private String name;

    private int age;

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
