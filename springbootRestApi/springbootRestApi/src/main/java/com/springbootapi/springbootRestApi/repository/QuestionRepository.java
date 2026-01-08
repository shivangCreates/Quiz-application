package com.springbootapi.springbootRestApi.repository;

import com.springbootapi.springbootRestApi.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByQuizid(Integer quizid);

   ;
}
