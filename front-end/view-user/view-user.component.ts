import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../User';
import { BookingService } from '../booking.service';
import { Booking } from '../Booking';

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.css']
})
export class ViewUserComponent implements OnInit{

  userId: any;
  user: any;
  bookings : Booking[] = [];
  count: number = 4;
  p: number = 1;
  constructor(private userService: UserService, private router: Router, private route : ActivatedRoute){}

  ngOnInit(): void {
    if(sessionStorage.getItem('userRole') === "USER"){
      this.userId = sessionStorage.getItem('userId');
      this.userService.readUserById(this.userId).subscribe(data => {
        this.user = data;
        console.log(this.user);
    })
    }
    else if(sessionStorage.getItem('userRole') === "ADMIN"){
      this.userId = this.route.snapshot.params['id'];
      this.userService.readUserById(this.userId).subscribe(data => {
        this.user = data;
        console.log(this.user);
      })
    }

    this.userService.getBookingByUserId(this.userId).subscribe(data => {
      this.bookings = data;
      console.log(this.bookings);
    })
  }

  back(){
    this.router.navigate(['see-all-users'])
  }

  adminLogin(){
    return sessionStorage.getItem('userRole') === "ADMIN";
  }
}
