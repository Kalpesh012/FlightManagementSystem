import { Component, OnInit } from '@angular/core';
import { Flight } from '../Flight';
import { FlightService } from '../flight.service';
import { Router } from '@angular/router';
import { AirportService } from '../airport.service';
import { Airport } from '../Airport';
import { CompanyService } from '../company.service';
import { Company } from '../Company';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { group } from '@angular/animations';
@Component({
  selector: 'app-create-flight',
  templateUrl: './create-flight.component.html',
  styleUrls: ['./create-flight.component.css']
})
export class CreateFlightComponent implements OnInit{

  constructor(private flightService: FlightService, private airportService: AirportService,
    private companyService: CompanyService, private router: Router, private formBuilder: FormBuilder) { }

    CreateFlights!:FormGroup
    submitted = false;
    flight : Flight = new Flight();
    airports : Airport[] = [];
    companys: Company[] = [];
    selectedAirportsIds: number[] = [];
    selectedCompanysIds: number[] = [];
    i: number;
    j: number;
    
    ngOnInit(): void {

      this.airportService.getAirportList().subscribe(data =>{
        this.airports = data,
        console.log(this.airports);
      })
      this.companyService.getCompanyList().subscribe(data =>{
        this.companys = data,
        console.log(this.companys)
      })
    }
    saveFlight(airportId: number, companyId: number){
      this.flightService.createFlight(this.flight, airportId, companyId).subscribe( data =>{
        console.log(data);
        this.goToFlightList();
      },
      error => console.log(error));
    }
  
    goToFlightList(){
      this.router.navigate(['/see-all-flights']);
    }
    
    back(){
      this.router.navigate(['see-all-flights'])
    }


}

