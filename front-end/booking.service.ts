import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Booking } from './Booking';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private httpClient: HttpClient) { }

  private baseURL = "http://localhost:8080/Booking";

  createBooking(obj : Booking, userId : number, flightId : number): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/saveBooking/${userId}/${flightId}`, obj);
  }

  getBookingList(): Observable<Booking[]> {
    return this.httpClient.get<Booking[]>(`${this.baseURL}/viewAllBooking`);
  }

  getBookingById(id : number) : Observable<Booking> {
    return this.httpClient.get<Booking>(`${this.baseURL}/viewBookingByOneId/${id}`);
  }

  removeBookingBetweenTwoDates(startDate: any, endDate: any) : Observable<any> {
    return this.httpClient.delete(`${this.baseURL}/deleteRecordBetweenDates?startDate=${startDate}&endDate=${endDate}`);
  }

  getOtp(userId:number): Observable<any>{
    return this.httpClient.get(`${this.baseURL}/getOtp/${userId}`)
  }
  getOtpForMobile(userId:number): Observable<any>{
    return this.httpClient.get(`${this.baseURL}/getOtpForMobile/${userId}`)
  }
}
