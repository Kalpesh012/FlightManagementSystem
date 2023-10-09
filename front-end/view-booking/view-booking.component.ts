import { Component, OnInit } from '@angular/core';
import { Booking } from '../Booking';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../booking.service';

@Component({
  selector: 'app-view-booking',
  templateUrl: './view-booking.component.html',
  styleUrls: ['./view-booking.component.css']
})
export class ViewBookingComponent implements OnInit{

  id: number = 0;
  particularBooking: Booking = new Booking();
  constructor(private route: ActivatedRoute, private bookingService: BookingService,
                private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
  
    this.bookingService.getBookingById(this.id).subscribe( record => {
      this.particularBooking = record;
      console.log( this.particularBooking);
    });
  }

  back(){
    this.router.navigate(['see-all-bookings'])
  }
}
