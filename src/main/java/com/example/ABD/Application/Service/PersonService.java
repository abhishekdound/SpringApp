package com.example.ABD.Application.Service;

import com.example.ABD.Application.Model.Person;
import com.example.ABD.Application.Repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepo pr;

    public List<Person> findAll(){

        return pr.findAll();

    }

    public Person findById(int id){

        return pr.findById(id).orElse(new Person());

    }

    public List<Person> findByName(String name){

        return pr.findByName(name);

    }

    public void save(Person p){

        pr.save(p);

    }

    public void delete(int id){

        pr.deleteById(id);

    }



}
