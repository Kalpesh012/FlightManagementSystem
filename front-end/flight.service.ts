import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Flight } from './Flight';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  private baseURL = "http://localhost:8080/FlightServ.com";

  constructor(private httpClient: HttpClient) { }
  
  getFlightById(id : number) : Observable<Flight> {
    return this.httpClient.get<Flight>(`${this.baseURL}/readFlightDetail/${id}`);
  }

  deleteAll(): Observable<Object[]> {
    return this.httpClient.delete<Object[]>(`${this.baseURL}/deleteAllFlightDetails`);
  }

  getFlightList(): Observable<Flight[]>{
    return this.httpClient.get<Flight[]>(`${this.baseURL}/readAllFlightDetails`);
  }

  deleteFlight(id : number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/deleteFlightDetail/${id}`);

  }

  findByComFLights(): Observable<Flight[]> {
    return this.httpClient.get<Flight[]>(`${this.baseURL}/FlightDetails/isCommercial`);
  }

  findByNonComFLights(): Observable<Flight[]> {
    return this.httpClient.get<Flight[]>(`${this.baseURL}/FlightDetails/isNonCommercial`);
}

  createFlight(obj : Flight, airportId: number, companyId: number): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/saveFlight/${airportId}/${companyId}`, obj);
  }
  updateFlight(id : number, flightObj : Flight) : Observable<Flight> {
    return this.httpClient.put<Flight>(`${this.baseURL}/updateFlightDetails/${id}`, flightObj);
  }
findByFlightName(flightName : String) : Observable<Flight[]>{

  //select * from tab name where col like %
  return this.httpClient.get<Flight[]>(`${this.baseURL}/readAllFlightDetails?flightName=${flightName}`);
}

sortByCost(direction: String, field: String): Observable<Flight[]>{
  return this.httpClient.get<Flight[]>(`${this.baseURL}/SortByTicketCost/${direction}?fieldName=${field}`);
}
sortByRating(direction: String, field: String): Observable<Flight[]>{
  return this.httpClient.get<Flight[]>(`${this.baseURL}/SortByRating/${direction}?fieldName=${field}`);
}
}
