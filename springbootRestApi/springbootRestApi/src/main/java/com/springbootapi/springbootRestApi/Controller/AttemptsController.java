package com.springbootapi.springbootRestApi.Controller;

import com.springbootapi.springbootRestApi.Entity.Attempt;
import com.springbootapi.springbootRestApi.Entity.Question;
import com.springbootapi.springbootRestApi.Entity.Quiz;
import com.springbootapi.springbootRestApi.repository.AttemptRepository;
import com.springbootapi.springbootRestApi.repository.QuestionRepository;
import com.springbootapi.springbootRestApi.repository.quizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attempt")
@CrossOrigin("*")
public class AttemptsController {

    @Autowired
    private AttemptRepository attemptRepo;

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private quizRepository quizRepo;

    // start attempt
    @PostMapping("/start")
    public Attempt startAttempt(@RequestBody Attempt attempt) {

        // Find quiz by quiz code
        Quiz quiz = quizRepo.findByQuizCode(attempt.getQuizcode());
        if (quiz == null) {
            throw new RuntimeException("Invalid Quiz Code");
        }
        attempt.setQuizId(quiz.getId());


        //  Set quiz timing details
        attempt.setStartTime(LocalDateTime.now());
        attempt.setQuizDuration(quiz.getQuizTime()); // minutes
        attempt.setStatus("IN_PROGRESS");

        // Initialize result values
        attempt.setCorrect(0);
        attempt.setWrong(0);
        attempt.setTotalQuestions(quiz.getQuesNumber());

        // 4. Save attempt
        return attemptRepo.save(attempt);
    }

   // submit fxn
    @PutMapping("/submit/{attemptId}")
    public Attempt submitAttempt(
            @PathVariable Integer attemptId,
            @RequestBody Map<Integer, String> answers
    ) {

        Attempt attempt = attemptRepo.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        int correct = 0;
        int wrong = 0;

        // check aswr
        for (Integer questionId : answers.keySet()) {
            Question q = questionRepo.findById(questionId).orElse(null);
            if (q != null) {
                if (q.getCorrectAns().equalsIgnoreCase(answers.get(questionId))) {
                    correct++;
                } else {
                    wrong++;
                }
            }
        }

        // 2. Update attempt result
        attempt.setCorrect(correct);
        attempt.setWrong(wrong);
        attempt.setTotalQuestions(correct + wrong);

        // 3. End quiz
        attempt.setEndTime(LocalDateTime.now());
        attempt.setStatus("COMPLETED");

        return attemptRepo.save(attempt);
    }


    @GetMapping("/questions/{quizid}")
    public List<Question> getQuestionsByQuizId(@PathVariable Integer quizid){
        return questionRepo.findByQuizid(quizid);

    }
    @GetMapping("/result/{attemptId}")
    public Attempt getResult(@PathVariable Integer attemptId) {
        return attemptRepo.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));
    }

}