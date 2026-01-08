import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-quizform',
  imports: [RouterLink, FormsModule, CommonModule],
  templateUrl: './quizform.html',
  styleUrl: './quizform.css',
})
export class Quizform {

  http = inject(HttpClient);
  router = inject(Router);

  name:String="";
  mobile:String="";
  email:String="";
  college:String="";
  quizCode:String="";




  StartTest(){
   const attemptData ={
    name:this.name,
    mobile:this.mobile,
    email:this.email,
    collegeName:this.college,
    quizcode:this.quizCode
   }
    this.http.post<any>('http://localhost:8080/api/attempt/start', attemptData).subscribe({
      next:(res)=>{
        localStorage.setItem('attemptId', res.id);
        localStorage.setItem('quizcode', res.quizcode);
        localStorage.setItem('quizid',res.quizId)
     
        this.router.navigate(['/quiz']);
      },
      error: (err) => {
        alert('Invalid Quiz Code');
      }
    });
  }

}
