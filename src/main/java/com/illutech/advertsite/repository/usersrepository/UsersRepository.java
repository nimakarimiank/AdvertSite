package com.illutech.advertsite.repository.usersrepository;

import com.illutech.advertsite.entities.Users;
import com.illutech.advertsite.entities.subentitites.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Collection<Users> findAllByUserType(UsersType type);
}
