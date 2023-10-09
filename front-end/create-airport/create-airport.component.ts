import { Component } from '@angular/core';
import { AirportService } from '../airport.service';
import { Airport } from '../Airport';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-airport',
  templateUrl: './create-airport.component.html',
  styleUrls: ['./create-airport.component.css']
})
export class CreateAirportComponent {

  constructor(private airportService: AirportService,
    private router: Router) { }

    airport : Airport = new Airport();
    
    
    
    saveAirport(){
      this.airportService.createAirport(this.airport).subscribe( data =>{
        console.log(data);
        this.goToAirportList();
      },
      error => console.log(error));
    }
  
    goToAirportList(){
      this.router.navigate(['/see-all-airport']);
    }
    
    onSubmit(){
      console.log(this.airport);
      this.saveAirport();
    }

    back(){
      this.router.navigate(['see-all-airport'])
    }
}
