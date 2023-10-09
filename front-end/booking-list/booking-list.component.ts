import { Component, OnInit } from '@angular/core';
import { Booking } from '../Booking';
import { BookingService } from '../booking.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent implements OnInit{

  bookings: Booking[] = [];
  count : number = 4;
  p : number = 1;
  startDate: any;
  endDate: any;

  constructor(private bookingService: BookingService,
    private router: Router) { }

  ngOnInit(): void {
    this.getAllBookings();
  }

  getAllBookings(): any{
    this.bookingService.getBookingList().subscribe(data => {
      this.bookings = data,
      console.log(this.bookings)
    })
  }

  viewBooking(id : number){
    this.router.navigate(['view-booking', id]);
  }

  removeBookings(): any{
      this.bookingService.removeBookingBetweenTwoDates(this.startDate, this.endDate).subscribe(details => {
        this.bookings= details;
        this.router.navigate(['see-all-bookings']);
      },
        error => {
          console.log(error);
        });
    }

    
}
