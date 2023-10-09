import { Component, OnInit } from '@angular/core';
import { FlightService } from '../flight.service';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../booking.service';
import { Booking } from '../Booking';
import { Flight } from '../Flight';
import { User } from '../User';
import { DatePipe, formatDate } from '@angular/common';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css'],
  providers: [DatePipe]
})
export class BookingComponent implements OnInit{

  id: number;
  ids: any;
  booking: Booking = new Booking();
  users: User[] = [];
  flight: Flight = new Flight();
  selectedUserIds: number[] = [];
  bookings: any;
   i: number;
  jd !: string;
  booked: number;
  otp:Number;
  mobileOtp;
  userId:any;

  constructor( private flightService: FlightService, private userService: UserService,
    private router: Router, private bookingService: BookingService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.flightService.getFlightById(this.id).subscribe(data => {
      this.flight = data,
      console.log(this.flight)  
    });
    this.userService.getUserList().subscribe(data => {
      this.users = data,
      console.log(this.users)
    })
    this.userId = sessionStorage.getItem('userId');
    this.bookingService.getOtp(this.userId).subscribe(data => {
      this.otp = Number(data);
      console.log(this.otp)
    })
    this.bookingService.getOtpForMobile(this.userId).subscribe(data => {
      this.mobileOtp = Number(data);
      console.log(this.mobileOtp)
    })
  }

  bookingTicket(userId: number, enteredOtp: any){
    console.log(enteredOtp);
    console.log(this.otp);
      if( enteredOtp == this.otp || enteredOtp == this.mobileOtp){
      alert('OTP verified successfully.');
      this.ids = sessionStorage.getItem('userId');
      this.bookingService.createBooking(this.booking, this.ids ,this.flight.recordNumber ).subscribe( 
      data =>{
      this.bookings = data;
      console.log(data);
      this.booked = this.bookings.bookingId;
      console.log(this.booked)
      this.router.navigate(['booking-details', this.booked])
      });
    }
    else{
      alert('Invalid OTP...');
      console.log("Varified failed...");
      this.router.navigate(['see-all-flights']);
    }
}

adminLogin(){
  return sessionStorage.getItem('userRole') === "ADMIN";
}

}