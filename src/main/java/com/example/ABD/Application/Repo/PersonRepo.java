package com.example.ABD.Application.Repo;

import com.example.ABD.Application.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person,Integer> {

    List<Person> findByName(String name);

}
