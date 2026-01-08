package com.springbootapi.springbootRestApi.repository;

import com.springbootapi.springbootRestApi.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface quizRepository extends JpaRepository<Quiz,Integer> {
    Quiz findByQuizCode(String quizCode);
}
