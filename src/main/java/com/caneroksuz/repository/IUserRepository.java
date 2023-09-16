package com.caneroksuz.repository;

import com.caneroksuz.repository.entity.User;
import com.caneroksuz.repository.enums.EUserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    Boolean existsByPasswordAndEmail(String password, String email);

    List<User> findAllByUserType(EUserType userType);

    List<User> findAllByOrderByName();

    List<User> findAllByOrderByNameDesc();

    List<User> findAllByNameContainingIgnoreCase(String value);

    List<User> findAllByEmailContainingIgnoreCase(String value);

    List<User> findAllByEmailEndingWith(String value);

    @Query("select u from User u where length(u.password)>?1")
    List<User> passwordLongerThan(int value);

    @Query("select u from User u where length(u.password)>:x")
    List<User> passwordLongerThan2(@Param("x") int value);

    @Query(value = "select * from tbl_user u where length(u.password)>?1", nativeQuery = true)
    List<User> passwordLongerThan3(@Param("x") int value);


}
