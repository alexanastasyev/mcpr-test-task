package org.example.mcprwebapp.address;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    @Query("SELECT a FROM Address a ORDER BY a.id")
    List<Address> getAll();

    @Query("SELECT a FROM Address a WHERE a.id = :id")
    Address getById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Address WHERE id = :id")
    void deleteById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE Address a SET a.street = :street, a.city = :city, a.state = :state, a.postalCode = :postalCode, " +
            "a.country = :country WHERE a.id = :id")
    void updateById(
        @Param("id") String id,
        @Param("street") String street,
        @Param("city") String city,
        @Param("state") String state,
        @Param("postalCode") String postalCode,
        @Param("country") String country
    );
}
