package org.example.mcprwebapp.person.database;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, String> {
    @Query("SELECT p FROM person p ORDER BY p.id")
    List<PersonEntity> getAll();

    @Query("SELECT p FROM person p WHERE p.id = :id")
    PersonEntity getById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("DELETE FROM person p WHERE p.id = :id")
    void deleteById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE person p SET p.name = :name, p.phone = :phone, p.email = :email, " +
            "p.addressId = :addressId  WHERE p.id = :id")
    void updateById(
        @Param("id") String id,
        @Param("name") String name,
        @Param("phone") String phone,
        @Param("email") String email,
        @Param("addressId") String addressId
    );
}
