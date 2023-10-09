import { Component } from '@angular/core';
import { Company } from '../Company';
import { CompanyService } from '../company.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent {

  constructor(private companyService: CompanyService,
    private router: Router) { }

    company : Company = new Company();
    
    
    
    saveFlight(){
      this.companyService.createCompany(this.company).subscribe( data =>{
        console.log(data);
        this.goToCompanyList();
      },
      error => console.log(error));
    }
  
    goToCompanyList(){
      this.router.navigate(['/see-all-company']);
    }
    
    onSubmit(){
      console.log(this.company);
      this.saveFlight();
    }

    back(){
      this.router.navigate(['see-all-company'])
    }
}
