import { Routes } from '@angular/router';
import { Login } from './login/login';
import { Dashboard } from './dashboard/dashboard';
import { Quiz } from './quiz/quiz';
import { Attempt } from './attempt/attempt';
import { Question } from './question/question';

export const routes: Routes = [
    {path:"",component:Login},
    {path:"dashboard",component:Dashboard},
    {path:"question/:id",component:Question},
    {path:"quiz",component:Quiz},
    {path:"attempt",component:Attempt}
];
