import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-quiz',
  standalone: true,
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './quiz.html',
  styleUrls: ['./quiz.css'],
})
export class Quiz {

  http = inject(HttpClient);
  route = inject(Router);
  ngOnInit() {
  this.showquiz();
}


  quizname: string = "";
  quizdesc: string = "";
  quizcode: string = "";
  quiztime: string = "";
  quizques: string = "";

  // ------------------- ADD QUIZ -------------------
  addQuiz() {

    if (!this.quizname || !this.quizdesc || !this.quizcode || !this.quiztime || !this.quizques) {
      alert("All fields are required!");
      return;
    }

    // Create form-data
    const formData = new FormData();
    formData.append("quizName", this.quizname);
    formData.append("quizDescription", this.quizdesc);
    formData.append("quizCode", this.quizcode);
    formData.append("quizTime", this.quiztime);
    formData.append("quesNumber", this.quizques);

    const apiurl = "http://localhost:8080/quiz/add";

    this.http.post(apiurl, formData).subscribe({
      next: (response) => {
        alert("Quiz Added Successfully!");
        this.showquiz(); // refresh list
      },
      error: (err) => {
        console.error(err);
        alert("Error while adding quiz!");
      }
    });
  }

  // ------------------- GET ALL QUIZ -------------------
  allquiz: any[] = [];

  showquiz() {
    const apiurl = "http://localhost:8080/quiz";
    this.http.get<any[]>(apiurl).subscribe({
      next: (data) => {
        this.allquiz = data;
      },
      error: () => {
        alert("Error loading quiz list");
      }
    });
  }

  // ------------------- ADD QUESTIONS -------------------
  addquestion(id: string) {
    this.route.navigate(["/question", id]);
  }
  // ------------------- DELETE QUIZ -------------------
deleteQuiz(id: number) {
  if (!confirm("Are you sure you want to delete this quiz?")) {
    return;
  }

  const apiurl = `http://localhost:8080/delete/${id}`;

  this.http.delete(apiurl).subscribe({
    next: () => {
      alert("Quiz Deleted Successfully!");
      this.showquiz();  // refresh quiz list
    },
    error: (err) => {
      console.error(err);
      alert("Error deleting quiz!");
    }
  });
}

  
}
