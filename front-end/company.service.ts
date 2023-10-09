import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from './Company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private baseURL = "http://localhost:8080/CompanyServ";

  constructor(private httpClient: HttpClient) { }

  createCompany(obj : Company): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/saveCompany`, obj);
  }

  getCompanyList(): Observable<Company[]> {
    return this.httpClient.get<Company[]>(`${this.baseURL}/readAllCompanyDetail`);
  }

  getCompanyById(id : number) : Observable<Company> {
    return this.httpClient.get<Company>(`${this.baseURL}/readCompany/${id}`);
  }

  updateCompany(id : number, companyObj : Company) : Observable<Company> {
    return this.httpClient.put<Company>(`${this.baseURL}/updateCompany/${id}`, companyObj);
  }

  deleteCompany(id : number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/deleteCompany/${id}`);
  }
  
  deleteAll(): Observable<any> {
    return this.httpClient.delete(`${this.baseURL}/deleteAllCompany`);
  }
}
