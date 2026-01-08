import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-result',
  imports: [RouterLink,FormsModule,CommonModule,HttpClientModule],
  templateUrl: './result.html',
  styleUrl: './result.css',
})
export class Result {
  constructor(private http:HttpClient){}
 apiurl=" http://localhost:8080/api/attempt/result";

 result:any;
  ngOnInit(){
    this.loadResult();
  }
  loadResult(){
    const attemptId = localStorage.getItem("attemptId");
    this.http.get<any>(`${this.apiurl}/${attemptId}`).subscribe({
      next:(data:any)=>{
        this.result=data;
      }
    })

  }

}
