package org.example.mcprwebapp.address;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AddressRepository extends CrudRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.id = :id")
    Address getById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Address WHERE id = ?1")
    void deleteById(@Param("id") String id);
}
