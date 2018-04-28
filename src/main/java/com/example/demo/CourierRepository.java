package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CourierRepository extends CrudRepository<Courier, String> {
    //Courier findById(long id);
    Courier findByCid (long id);
}
