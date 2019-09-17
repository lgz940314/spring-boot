package com.krupp.springbootmongodb.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Programmer {

    @Id
    private String name;

    private int age;

    private float salary;

    private Date birthday;

}
