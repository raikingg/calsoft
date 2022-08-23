package com.shivan.calsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shivan.calsoft.model.UserData;
@Repository
public interface UserRepository extends JpaRepository<UserData, Integer>{

}
