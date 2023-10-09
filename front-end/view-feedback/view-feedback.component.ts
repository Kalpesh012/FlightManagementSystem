import { Component, OnInit } from '@angular/core';
import { Feedback } from '../Feedback';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { FeedbackService } from '../feedback.service';

@Component({
  selector: 'app-view-feedback',
  templateUrl: './view-feedback.component.html',
  styleUrls: ['./view-feedback.component.css']
})
export class ViewFeedbackComponent implements OnInit{

  id: number = 0;
  particularFeedback: Feedback = new Feedback();
  constructor(private route: ActivatedRoute, private feedbackService: FeedbackService,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
  
    this.feedbackService.getFeedbackById(this.id).subscribe( record => {
      this.particularFeedback = record;
      console.log( this.particularFeedback);
    });
  }
  
  back(){
    this.router.navigate(['see-all-feedbacks'])
  }
}
