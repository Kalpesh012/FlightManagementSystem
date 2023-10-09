import { Component, OnInit } from '@angular/core';
import { AirportService } from '../airport.service';
import { Router } from '@angular/router';
import { Airport } from '../Airport';

@Component({
  selector: 'app-airport-list',
  templateUrl: './airport-list.component.html',
  styleUrls: ['./airport-list.component.css']
})
export class AirportListComponent implements OnInit{

  count : number = 4;
  p : number = 1;

  constructor(private airportService: AirportService,
    private router: Router){};

  airports : Airport[] = [];

  ngOnInit(): void {
    this.getAllAirportDetails();
  }

  private getAllAirportDetails() : any{
    this.airportService.getAirportList().subscribe(data => {
      this.airports = data;
      console.log(data);
    });
  }

  viewAirport(id : number){   
    this.router.navigate(['view-airport-details', id]); 
  }

}
