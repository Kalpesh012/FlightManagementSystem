import { Component, OnInit } from '@angular/core';
import { Feedback } from '../Feedback';
import { FeedbackService } from '../feedback.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-feedback-list',
  templateUrl: './feedback-list.component.html',
  styleUrls: ['./feedback-list.component.css']
})
export class FeedbackListComponent implements OnInit{

  feedbacks: Feedback[] = [];
  Flights:any;
  count : number = 4;
  p : number = 1;

  constructor(private feedbackService: FeedbackService,
    private router: Router) { }

  ngOnInit(): void {
    this.getAllFeedbacks();
  }

  getAllFeedbacks(): any {
    this.feedbackService.getFeedbackList().subscribe(data => {
      this.feedbacks = data,
      console.log(this.feedbacks)
    })
  }

  deleteFeedback(id : number){
    var status = confirm("Are you sure to delete this record?");
    if (status == true) {
    this.feedbackService.deleteFeedback(id).subscribe( data => {
      this.getAllFeedbacks();
    })
  }
    else{
      this.getAllFeedbacks();
    }
  
  }

  viewFeedback(id : number){
    this.router.navigate(['view-feedback', id]);
  }
}
