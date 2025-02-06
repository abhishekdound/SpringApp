package com.example.ABD.Application.Controller;

import com.example.ABD.Application.Model.Person;
import com.example.ABD.Application.Model.User;
import com.example.ABD.Application.Service.PersonService;
import com.example.ABD.Application.Service.UserPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private PersonService ps;

    @Autowired
    private UserPassService up;

    @GetMapping("person")
    public List<Person> p(){

        return ps.findAll();

    }

    @GetMapping("person/{id}")
    public Person p1(@PathVariable("id")int id){

        return ps.findById(id);

    }

    @GetMapping("person/{name}")
    public List<Person> p2(@PathVariable("name") String name){

        return ps.findByName(name);

    }

    @PostMapping("person")
    public Person p4(@RequestBody Person p){

        ps.save(p);

        return ps.findById(p.getId());

    }

    @PutMapping("person")
    public Person p3(@RequestBody Person p){

        ps.save(p);

        return ps.findById(p.getId());

    }

    @DeleteMapping("person/{id}")
    public String p5(@PathVariable("id") int id){

        ps.delete(id);

        return "Deleted the person with id " + id;

    }

    @PostMapping("register")
    public User set(@RequestBody User user){

        up.feef(user);

        return user;
    }

}
