import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './User';
import { Observable } from 'rxjs';
import { Booking } from './Booking';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = "http://localhost:8080/FlightServ.com";

  constructor(private httpClient: HttpClient) {}

  signup(user:User):Observable<any>
  {
     const headers={'content-type':'application/json'};
     const body=JSON.stringify(user);
     return this.httpClient.post(`${this.baseURL}/signup`,body,{'headers':headers});
  }
  isUserLoggedIn() {
    let status = sessionStorage.getItem('loginStatus')  //active
    return !(status === null)
  }
  signin(email:any, password:any):Observable<any>
  {
    const params=new HttpParams().set('email',email).set('password',password);
    console.log(params);
    return this.httpClient.get(`${this.baseURL}/login`,{params});
  }
  // logOut() {
  //   sessionStorage.removeItem('loginStatus')
  //   //this.router.navigate['/sigin'];
  //   }

  readUserById(id: number): Observable<User>{
    return this.httpClient.get<User>(`${this.baseURL}/readUserById/${id}`);
  }
  getUserList(): Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseURL}/readAllUsers`);
  }
  getBookingByUserId(id:number): Observable<Booking[]>{
    return this.httpClient.get<Booking[]>(`${this.baseURL}/readBookingDetails/${id}`)
  }
}
