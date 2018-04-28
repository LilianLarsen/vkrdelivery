package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdRepository extends CrudRepository<Ord, String> {
    Ord findByOid(long id);
    void deleteByOid (long id);
    List<Ord> findByClientId (long clientId);
    List<Ord> findByCourierId (long courierId);
}