import { Component, OnInit } from '@angular/core';
import { Airport } from '../Airport';
import { ActivatedRoute, Router } from '@angular/router';
import { AirportService } from '../airport.service';

@Component({
  selector: 'app-view-airport',
  templateUrl: './view-airport.component.html',
  styleUrls: ['./view-airport.component.css']
})
export class ViewAirportComponent implements OnInit{

  id: number = 0;
  particularAirport: Airport = new Airport();
  constructor(private route: ActivatedRoute, private airportService: AirportService,
                private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
  
    this.airportService.getAirportById(this.id).subscribe( record => {
      this.particularAirport = record;
      console.log( this.particularAirport);
    });
  }

  back(){
    this.router.navigate(['see-all-airport'])
  }
}
