package com.springbootapi.springbootRestApi.repository;

import com.springbootapi.springbootRestApi.Entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository  extends JpaRepository<Attempt, Integer> {

}
