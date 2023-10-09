import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FlightListComponent } from './flight-list/flight-list.component';
import { CreateFlightComponent } from './create-flight/create-flight.component';
import { ViewFlightComponent } from './view-flight/view-flight.component';
import {UpdateFlightComponent} from'./update-flight/update-flight.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { AuthGaurdService } from './auth-gaurd.service';
import { AppComponent } from './app.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { CompanyListComponent } from './company-list/company-list.component';
import { ViewCompanyComponent } from './view-company/view-company.component';
import { CompanyUpdateComponent } from './company-update/company-update.component';
import { CreateCompanyComponent } from './create-company/create-company.component';
import { AirportListComponent } from './airport-list/airport-list.component';
import { CreateAirportComponent } from './create-airport/create-airport.component';
import { ViewAirportComponent } from './view-airport/view-airport.component';
import { FeedbackListComponent } from './feedback-list/feedback-list.component';
import { ViewFeedbackComponent } from './view-feedback/view-feedback.component';
import { BookingComponent } from './booking/booking.component';
import { BookingListComponent } from './booking-list/booking-list.component';
import { ViewBookingComponent } from './view-booking/view-booking.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { BookingDetailsComponent } from './booking-details/booking-details.component';
import { CargoProductsComponent } from './cargo-products/cargo-products.component';
import { ConditionsOfContractComponent } from './conditions-of-contract/conditions-of-contract.component';
import { RecognitionsComponent } from './recognitions/recognitions.component';
import { UserListComponent } from './user-list/user-list.component';
import { ViewUserComponent } from './view-user/view-user.component';

const routes: Routes = [
  {path: 'see-all-flights', component: FlightListComponent,canActivate: [AuthGaurdService]},
  {path: 'create-flight', component: CreateFlightComponent,canActivate: [AuthGaurdService]},
  {path: '', redirectTo: 'signin', pathMatch: 'full'},
  {path: 'update-flight/:id', component: UpdateFlightComponent,canActivate: [AuthGaurdService]},
  {path: 'view-flight-details/:id', component: ViewFlightComponent, canActivate: [AuthGaurdService]},
  {path:'signin', component: SignInComponent},
  {path:'signup', component: SignUpComponent},
  {path: 'log-out', component: AppComponent,canActivate: [AuthGaurdService]},
  {path: 'feed-back/:id', component: FeedbackComponent},
  {path: 'see-all-company', component: CompanyListComponent,canActivate: [AuthGaurdService]},
  {path: 'view-company-details/:id', component: ViewCompanyComponent,canActivate: [AuthGaurdService]},
  {path: 'company-update/:id', component: CompanyUpdateComponent,canActivate: [AuthGaurdService]},
  {path: 'create-company', component: CreateCompanyComponent,canActivate: [AuthGaurdService]},
  {path: 'see-all-airport', component: AirportListComponent,canActivate: [AuthGaurdService]},
  {path: 'create-airport', component: CreateAirportComponent,canActivate: [AuthGaurdService]},
  {path: 'view-airport-details/:id', component: ViewAirportComponent,canActivate: [AuthGaurdService]},
  {path: 'see-all-feedbacks', component: FeedbackListComponent,canActivate: [AuthGaurdService]},
  {path: 'view-feedback/:id', component: ViewFeedbackComponent,canActivate: [AuthGaurdService]},
  {path: 'booking/:id', component: BookingComponent,canActivate: [AuthGaurdService]},
  {path: 'see-all-bookings', component: BookingListComponent,canActivate: [AuthGaurdService]},
  {path: 'view-booking/:id', component: ViewBookingComponent,canActivate: [AuthGaurdService]},
  {path: 'about-us', component: AboutUsComponent},
  {path: 'booking-details/:booked', component: BookingDetailsComponent,canActivate: [AuthGaurdService]},
  {path: 'about-us/cargo', component:CargoProductsComponent},
  {path: 'about-us/terms', component:ConditionsOfContractComponent},
  {path: 'about-us/Recognition', component: RecognitionsComponent},
  {path: 'see-all-users', component: UserListComponent,canActivate: [AuthGaurdService]},
  {path: 'view-user/:id', component: ViewUserComponent,canActivate: [AuthGaurdService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
