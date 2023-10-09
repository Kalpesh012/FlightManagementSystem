import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../company.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Company } from '../Company';

@Component({
  selector: 'app-company-update',
  templateUrl: './company-update.component.html',
  styleUrls: ['./company-update.component.css']
})
export class CompanyUpdateComponent implements OnInit{
id: number = 0;

company: Company = new Company();

constructor(private companyService: CompanyService,
  private route: ActivatedRoute,
  private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];  
    
    this.companyService.getCompanyById(this.id).subscribe(data => {
      this.company = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.companyService.updateCompany(this.id, this.company).subscribe( data =>{
      this.getAllCompanyDetails();
    }
    , error => console.log(error));
  }

  getAllCompanyDetails(){
    this.router.navigate(['/see-all-company']);
  }

  back(){
    this.router.navigate(['see-all-company'])
  }
}
