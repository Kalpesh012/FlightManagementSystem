import { Component, OnInit } from '@angular/core';
import { FlightService } from '../flight.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Flight } from '../Flight';

@Component({
  selector: 'app-view-flight',
  templateUrl: './view-flight.component.html',
  styleUrls: ['./view-flight.component.css']
})
export class ViewFlightComponent implements OnInit{

  id: number = 0;
  particularFlight: Flight = new Flight();
  particularCompany: any;
  particularAirport: any;
  constructor(private route: ActivatedRoute, private flightService: FlightService,
              private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
  console.log(this.id);
    this.flightService.getFlightById(this.id).subscribe( data => {
      console.log(data);
      this.particularFlight = data,
      this.particularCompany = data.ownerCompanyName,
      this.particularAirport = data.airport,
      console.log(this.particularFlight),
      console.log(this.particularCompany),
      console.log(this.particularAirport);
    });
  }

  booking(recordNumber: number): any {
    this.router.navigate(['booking', this.particularFlight.recordNumber]);
  }

  back(){
    this.router.navigate(['see-all-flights'])
  }
  }
