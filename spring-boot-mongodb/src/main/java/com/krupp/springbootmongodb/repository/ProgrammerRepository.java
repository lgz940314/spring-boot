package com.krupp.springbootmongodb.repository;


import com.krupp.springbootmongodb.bean.Programmer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProgrammerRepository extends MongoRepository<Programmer, String> {

    void deleteAllByName(String name);

    Programmer findAllByName(String names);

    Programmer findByNameAndAge(String name, int age);

}
