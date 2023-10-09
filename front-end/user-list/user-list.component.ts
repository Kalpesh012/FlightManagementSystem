import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../User';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit{

  users: User[] = [];
  count: number = 4;
  p: number = 1;

  constructor(private router: Router, private userService: UserService){}

  ngOnInit(): void {
    this.userService.getUserList().subscribe(data => {
      this.users = data,
      console.log(this.users);
    })
  }

  deleteUser(userId: number){
    
  }
  viewUser(userId: number){
    this.router.navigate(['view-user', userId])
  }
}
