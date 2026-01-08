package com.springbootapi.springbootRestApi.Controller;

import com.springbootapi.springbootRestApi.Entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springbootapi.springbootRestApi.repository.quizRepository;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class QuizController {

    @Autowired
    quizRepository repo;

    // GET all quiz
    @GetMapping("/quiz")
    public List<Quiz> getAllQuiz() {
        return repo.findAll();
    }

    // CREATE quiz using FORM-DATA
    @PostMapping("/quiz/add")
    public Quiz createQuiz(@ModelAttribute Quiz quiz) {
        return repo.save(quiz);
    }

    // UPDATE quiz using FORM-DATA
    @PutMapping("/update/{id}")
    public Quiz updateQuiz(@PathVariable Integer id, @ModelAttribute Quiz quizDetails) {

        Quiz existing = repo.findById(id).orElse(null);

        if (existing != null) {
            existing.setQuesNumber(quizDetails.getQuesNumber());
            existing.setQuizCode(quizDetails.getQuizCode());
            existing.setQuizTime(quizDetails.getQuizTime());
            existing.setQuizDescription(quizDetails.getQuizDescription());
            existing.setQuizName(quizDetails.getQuizName());
            return repo.save(existing);
        }
        return null;
    }

    // DELETE quiz
    @DeleteMapping("/delete/{id}")
    public String deleteQuiz(@PathVariable Integer id) {
        repo.deleteById(id);
        return "Quiz Deleted: " + id;
    }
}
