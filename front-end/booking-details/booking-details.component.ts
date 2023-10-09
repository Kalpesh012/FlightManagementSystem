import { Component, OnInit } from '@angular/core';
import { Booking } from '../Booking';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../booking.service';

@Component({
  selector: 'app-booking-details',
  templateUrl: './booking-details.component.html',
  styleUrls: ['./booking-details.component.css']
})
export class BookingDetailsComponent implements OnInit{
  id: number;
  particularBooking: Booking = new Booking();
  constructor(private route: ActivatedRoute, private bookingService: BookingService,
                private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['booked'];
  
    this.bookingService.getBookingById(this.id).subscribe( record => {
      this.particularBooking = record;
      console.log( this.particularBooking);
    });
  }

  back(){
    this.router.navigate(['see-all-flights'])
  }
}
