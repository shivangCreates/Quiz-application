import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-quiz',
  imports: [RouterLink, CommonModule],
  templateUrl: './quiz.html',
  styleUrl: './quiz.css',
})
export class Quiz {

  http = inject(HttpClient);
  router = inject(Router);

  questions: any[] = [];
  currentIndex: number = 0;
  currentQuestion: any;

  answers: { [key: number]: string } = {};

  ngOnInit() {

    this.getQestions();


  }
  getQestions() {

    const quizid = localStorage.getItem('quizid');
    if (!quizid) {
      alert('Quiz Id missing');
      return;
    }
    this.http.get(`http://localhost:8080/api/attempt/questions/${quizid}`).subscribe({
      next: (res: any) => {
        this.questions=res;
        if (this.questions.length > 0) {
          this.currentIndex = 0;
          this.currentQuestion = this.questions[0];
        } else {
          alert('No questions found');
        }

      },
      error: (err) => {
        alert("invalid API");
      }
    });
  }
  prevQuestion() {
    this.currentIndex--;
    this.currentQuestion = this.questions[this.currentIndex];

  }
  nextQuestion() {
    this.currentIndex++;
    this.currentQuestion = this.questions[this.currentIndex];
  }
  submitQuiz() {
    const attemptId = localStorage.getItem("attemptId");
    this.http.put(`http://localhost:8080/api/attempt/submit/${attemptId}`, this.answers).subscribe({
      next: (res: any) => {
       localStorage.setItem('correct',res.correct);
       localStorage.setItem('wrong',res.wrong);
       localStorage.setItem('name',res.name);

        this.router.navigate(['/result']);
      },
      error: (err) => {
        console.error(err);
        alert('Submit failed');
      }
    })


  }
  saveAnswer(option: string) {
    const questionId = this.currentQuestion.id;
    this.answers[questionId] = option;
    console.log('Selected answers:', this.answers);
  }

}