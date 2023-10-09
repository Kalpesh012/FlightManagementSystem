import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Feedback } from '../Feedback';
import { FeedbackService } from '../feedback.service';
import { FlightService } from '../flight.service';
import { Flight } from '../Flight';
import { User } from '../User';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit{

  constructor(public feedbackservice: FeedbackService, private router: Router,private userService: UserService, 
    private flightService: FlightService, private route: ActivatedRoute){

  }

  id: number;
  flights: Flight[] = [];
  i:number;
  user: User[] = [];
  selectedFlightIds: number[] = [];


  ngOnInit(): void {
    this.flightService.getFlightList().subscribe(data => {
     this.flights = data;
      console.log(this.flights);
    })
    this.id = this.route.snapshot.params['id'];
  }

  feedback: Feedback = new Feedback();

  signIn(){
    this.router.navigate(['signin']);
  }

  onSubmit( recordNumber: number){
    console.log(recordNumber);
    this.feedbackservice.createFeedback(this.feedback,recordNumber, this.id).subscribe( data =>{
      console.log(data);
      this.signIn();
    },
    error => console.log(error));
  
}
}
