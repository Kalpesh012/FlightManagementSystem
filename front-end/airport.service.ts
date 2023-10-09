import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Airport } from './Airport';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AirportService {
 
  private baseURL = "http://localhost:8080/AirportServ";

  constructor(private httpClient: HttpClient) { }

  createAirport(obj : Airport): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/saveAirport`, obj);
  }

  getAirportList(): Observable<Airport[]> {
    return this.httpClient.get<Airport[]>(`${this.baseURL}/readAllAirportDetails`);
  }

  getAirportById(id : number) : Observable<Airport> {
    return this.httpClient.get<Airport>(`${this.baseURL}/readairportDetail/${id}`);
  }
}
