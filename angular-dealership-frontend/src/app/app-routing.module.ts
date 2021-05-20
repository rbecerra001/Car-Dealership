import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MakesComponent } from './makes/makes.component';
import { ModelsComponent } from "./models/models.component";
import { SignupComponent } from './signup/signup.component';
import { LogoutComponent} from './logout/logout.component';
import { MakeComponent } from './make/make.component';

const routes: Routes = [
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'logout',
    component: LogoutComponent
  },
  {
    path: 'makes',
    component: MakesComponent
  },
  {
    path: 'makes/:id',
    component: MakeComponent
  },
  {
    path: 'models',
    component: ModelsComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
