import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from './User';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Zap Travels';
  id: any;
  users: any;
  role:string;

  constructor( public userService : UserService,
    private router: Router){
  }


  logout(){
    var status = confirm("Are you sure to log out?");
    if (status == true) {
      sessionStorage.removeItem('loginStatus');
      sessionStorage.removeItem('userRole');
      sessionStorage.removeItem('role');
    this.router.navigate(['feed-back',sessionStorage.getItem('userId')])
    sessionStorage.removeItem('userId');
    }
    else{
      this.goToFeedback();
    }
  }
  goToFeedback(){
    this.router.navigate(['/see-all-flights']);
  }

  adminLogin(){
    return sessionStorage.getItem('userRole') === "ADMIN";
  }
  userLogin(){
    return sessionStorage.getItem('userRole') === "USER";
  }

  viewUser(){
    this.router.navigate(['view-user', sessionStorage.getItem('userId')])
  }
}
