import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Company } from '../Company';
import { CompanyService } from '../company.service';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})

export class CompanyListComponent implements OnInit{

  count : number = 4;
  p : number = 1;

  constructor(private companyService: CompanyService,
        private router: Router){};

  companys : Company[] = [];

  ngOnInit(): void {
    this.getAllCompanyDetails();
  }

  private getAllCompanyDetails() : any{
    this.companyService.getCompanyList().subscribe(data => {
      this.companys = data;
      console.log(data);
    });
  }

  viewCompany(id : number){   
    this.router.navigate(['view-company-details', id]); 
  }

  updateCompany(id : number){
    this.router.navigate(['company-update', id]);
  }

  deleteCompany(id : number){
    var status = confirm("Are you sure to delete this record?");
    if (status == true) {
    this.companyService.deleteCompany(id).subscribe( data => {
      this.getAllCompanyDetails();
    })
  }
    else{
      this.getAllCompanyDetails();
    }
  
  }

  removeAllCompanys(){
    var status = confirm("Are you sure to delete all the records?");
    if (status == true) {
      this.companyService.deleteAll().subscribe(details => {
        console.log(details);  //null
        this.getAllCompanyDetails();
      },
        error => {
          console.log(error);
        })
    }
    else{
    this.getAllCompanyDetails();
  }
  }
}
