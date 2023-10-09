import { Component, OnInit } from '@angular/core';
import { Company } from '../Company';
import { ActivatedRoute, Router } from '@angular/router';
import { CompanyService } from '../company.service';

@Component({
  selector: 'app-view-company',
  templateUrl: './view-company.component.html',
  styleUrls: ['./view-company.component.css']
})
export class ViewCompanyComponent implements OnInit{

  id: number = 0;
  particularCompany: Company = new Company();
  constructor(private route: ActivatedRoute, private companyService: CompanyService,
              private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
  
    this.companyService.getCompanyById(this.id).subscribe( record => {
      this.particularCompany = record;
      console.log( this.particularCompany);
    });
  }

  back(){
    this.router.navigate(['see-all-company'])
  }
}
