import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Feedback } from './Feedback';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  private baseURL = "http://localhost:8080/FeedBackServ";

  constructor(private httpClient: HttpClient) { }

  getFeedbackByUserId(id : number) : Observable<Feedback> {
    return this.httpClient.get<Feedback>(`${this.baseURL}/readOneFeedbackByUser/${id}`);
  }

  getFeedbackByflightId(id : number) : Observable<Feedback> {
    return this.httpClient.get<Feedback>(`${this.baseURL}/readOneFeedbackByFlight/${id}`);
  }

  getFeedbackList(): Observable<Feedback[]>{
    return this.httpClient.get<Feedback[]>(`${this.baseURL}/readAllFeedback`);
  }
  getFeedbackById(id : number): Observable<Feedback>{
    return this.httpClient.get<Feedback>(`${this.baseURL}/readFeedbackByFeedbackId/${id}`);
  }

  deleteFeedback(id : number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/DeleteFeedback/${id}`);

  }

  createFeedback(obj : Feedback, flightId, userId): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/saveFeedback/${flightId}/${userId}`, obj);
  }
}
