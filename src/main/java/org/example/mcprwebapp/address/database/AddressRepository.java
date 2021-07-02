package org.example.mcprwebapp.address.database;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressRepository extends CrudRepository<AddressEntity, String> {
    @Query("SELECT a FROM address a ORDER BY a.id")
    List<AddressEntity> getAll();

    @Query("SELECT a FROM address a WHERE a.id = :id")
    AddressEntity getById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("DELETE FROM address a WHERE a.id = :id")
    void deleteById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE address a SET a.street = :street, a.city = :city, a.state = :state, a.postalCode = :postalCode, " +
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
