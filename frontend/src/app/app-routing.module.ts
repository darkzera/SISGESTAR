import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuard} from './guard/auth.guard';
import {GuestGuard} from './guard/guest.guard';
import {LoginComponent} from './login/login.component';

const routes: Routes = [
    { path: '', redirectTo: 'admin', pathMatch: 'full'},
    { path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule), canActivate: [AuthGuard]},
    { path: 'login', component: LoginComponent, canActivate: [GuestGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
