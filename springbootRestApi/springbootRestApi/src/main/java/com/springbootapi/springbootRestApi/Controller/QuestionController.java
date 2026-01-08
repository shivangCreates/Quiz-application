package com.springbootapi.springbootRestApi.Controller;

import com.springbootapi.springbootRestApi.Entity.Question;
import com.springbootapi.springbootRestApi.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepo;

    // POST - Add question using form-data
    @PostMapping("/add")
    public Question addQuestion(
            @RequestParam Integer quizId,
            @RequestParam String question,
            @RequestParam String optionA,
            @RequestParam String optionB,
            @RequestParam String optionC,
            @RequestParam String optionD,
            @RequestParam String correctAns
    ) {
        Question q = new Question();
        q.setQuizid(quizId);
        q.setQuestion(question);
        q.setOptionA(optionA);
        q.setOptionB(optionB);
        q.setOptionC(optionC);
        q.setOptionD(optionD);
        q.setCorrectAns(correctAns);

        return questionRepo.save(q);
    }

    // GET ALL questions (optional)
    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    // GET ALL questions of a specific quiz
    @GetMapping("/quiz/{quizid}")
    public List<Question> getByQuizId(@PathVariable Integer quizid) {
        return questionRepo.findByQuizid(quizid);
    }

    // GET single question by ID
    @GetMapping("/get/{id}")
    public Question getById(@PathVariable Integer id) {
        return questionRepo.findById(id).orElse(null);
    }

    // UPDATE question
    @PutMapping("/update/{id}")
    public Question updateQuestion(
            @PathVariable Integer id,
            @RequestParam(required = false) Integer quizId,
            @RequestParam(required = false) String question,
            @RequestParam(required = false) String optionA,
            @RequestParam(required = false) String optionB,
            @RequestParam(required = false) String optionC,
            @RequestParam(required = false) String optionD,
            @RequestParam(required = false) String correctAns
    ) {
        Question existing = questionRepo.findById(id).orElse(null);
        if (existing == null) return null;

        if (quizId != null) existing.setQuizid(quizId);
        if (question != null) existing.setQuestion(question);
        if (optionA != null) existing.setOptionA(optionA);
        if (optionB != null) existing.setOptionB(optionB);
        if (optionC != null) existing.setOptionC(optionC);
        if (optionD != null) existing.setOptionD(optionD);
        if (correctAns != null) existing.setCorrectAns(correctAns);

        return questionRepo.save(existing);
    }

    // DELETE question
    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Integer id) {
        if (questionRepo.existsById(id)) {
            questionRepo.deleteById(id);
            return "Question deleted successfully!";
        }
        return "Question not found!";
    }
}
