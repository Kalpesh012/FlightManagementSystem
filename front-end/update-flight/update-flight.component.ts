import { Component, OnInit } from '@angular/core';
import { FlightService } from '../flight.service';
import { Flight } from '../Flight';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-flight',
  templateUrl: './update-flight.component.html',
  styleUrls: ['./update-flight.component.css']
})
export class UpdateFlightComponent implements OnInit{
  id: number = 0;
  flight: Flight = new Flight();
  constructor(private flightService: FlightService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];  //4

    this.flightService.getFlightById(this.id).subscribe(data => {
      this.flight = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.flightService.updateFlight(this.id, this.flight).subscribe( data =>{
      this.getAllFlightDetails();
    }
    , error => console.log(error));
  }

  getAllFlightDetails(){
    this.router.navigate(['/see-all-flights']);
  }

  back(){
    this.router.navigate(['see-all-flights'])
  }
}

