import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-question',
  templateUrl: './question.html',
  styleUrl: './question.css',
  imports: [RouterLink, FormsModule],
})
export class Question {

  http = inject(HttpClient);
  route = inject(ActivatedRoute);

  quizid: any = null;

  id: any = null;
  question: string = "";
  optiona: string = "";
  optionb: string = "";
  optionc: string = "";
  optiond: string = "";
  correct: string = "";

  questionList: any[] = [];

  baseUrl = "http://localhost:8080/question";

  ngOnInit() {
    this.quizid = this.route.snapshot.paramMap.get("id");
    this.getAllQuestions();
  }

//Showing all Questions 

  getAllQuestions() {
    const apiurl = `${this.baseUrl}/quiz/${this.quizid}`;

    this.http.get<any[]>(apiurl).subscribe({
      next: (res) => {
        this.questionList = res;
      },
      error: () => {
        alert("Error loading questionList");
      }
    });
  }

  // -------------------------------
  // ADD NEW QUESTION
  // -------------------------------
  saveQuestion() {

    let formData = new FormData();
    formData.append("quizId", this.quizid);  // FIXED
    formData.append("question", this.question);
    formData.append("optionA", this.optiona);
    formData.append("optionB", this.optionb);
    formData.append("optionC", this.optionc);
    formData.append("optionD", this.optiond);
    formData.append("correctAns", this.correct);

    this.http.post(`${this.baseUrl}/add`, formData).subscribe(res => {
      alert("Question Added Successfully!");
      this.getAllQuestions();
    });
  }

//Edit Questions 
  editQuestion(id: any) {

    this.http.get(`${this.baseUrl}/get/${id}`).subscribe((q: any) => {   // FIXED

      this.id = q.id;
      this.question = q.question;
      this.optiona = q.optionA;
      this.optionb = q.optionB;
      this.optionc = q.optionC;
      this.optiond = q.optionD;
      this.correct = q.correctAns;

      // Open Modal
      const modal = document.getElementById("exampleModal");
      if (modal) {
        // @ts-ignore
        let modalInstance = new bootstrap.Modal(modal);
        modalInstance.show();
      }
    });
  }

  // -------------------------------
  // UPDATE QUESTION
  // -------------------------------
  updateQuestion() {

    let formData = new FormData();
    formData.append("question", this.question);
    formData.append("optionA", this.optiona);
    formData.append("optionB", this.optionb);
    formData.append("optionC", this.optionc);
    formData.append("optionD", this.optiond);
    formData.append("correctAns", this.correct);

    this.http.put(`${this.baseUrl}/update/${this.id}`, formData).subscribe(res => {
      alert("Updated Successfully!");
      this.getAllQuestions();
    });
  }

  // -------------------------------
  // DELETE
  // -------------------------------
  deleteQuestion(id: any) {

    if (!confirm("Do you really want to delete this question?")) return;

    this.http.delete(`${this.baseUrl}/delete/${id}`).subscribe(res => {
      alert("Deleted Successfully!");
      this.getAllQuestions();
    });
  }
}
