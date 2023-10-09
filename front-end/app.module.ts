import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FlightListComponent} from './flight-list/flight-list.component';
import { CreateFlightComponent } from './create-flight/create-flight.component';
import { FormsModule } from '@angular/forms';
import { ViewFlightComponent } from './view-flight/view-flight.component';
import { UpdateFlightComponent } from './update-flight/update-flight.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { FeedbackComponent } from './feedback/feedback.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { CompanyListComponent } from './company-list/company-list.component';
import { CompanyUpdateComponent } from './company-update/company-update.component';
import { CreateCompanyComponent } from './create-company/create-company.component';
import { ViewCompanyComponent } from './view-company/view-company.component';
import { AirportListComponent } from './airport-list/airport-list.component';
import { CreateAirportComponent } from './create-airport/create-airport.component';
import { ViewAirportComponent } from './view-airport/view-airport.component';
import { FeedbackListComponent } from './feedback-list/feedback-list.component';
import { ViewFeedbackComponent } from './view-feedback/view-feedback.component';
import { BookingComponent } from './booking/booking.component';
import { BookingListComponent } from './booking-list/booking-list.component';
import { ViewBookingComponent } from './view-booking/view-booking.component';
import { BookingDetailsComponent } from './booking-details/booking-details.component';
import { DatePipe } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RecognitionsComponent } from './recognitions/recognitions.component';
import { CargoProductsComponent } from './cargo-products/cargo-products.component';
import { ConditionsOfContractComponent } from './conditions-of-contract/conditions-of-contract.component';
import { ViewUserComponent } from './view-user/view-user.component';
import { UserListComponent } from './user-list/user-list.component';

@NgModule({
  declarations: [
    AppComponent,
  
    CreateFlightComponent,
    FlightListComponent,
    ViewFlightComponent,
    UpdateFlightComponent,
    SignInComponent,
    SignUpComponent,
    FeedbackComponent,
    AboutUsComponent,
    CompanyListComponent,
    CompanyUpdateComponent,
    CreateCompanyComponent,
    ViewCompanyComponent,
    AirportListComponent,
    CreateAirportComponent,
    ViewAirportComponent,
    FeedbackListComponent,
    ViewFeedbackComponent,
    BookingComponent,
    BookingListComponent,
    ViewBookingComponent,
    BookingDetailsComponent,
    RecognitionsComponent,
    CargoProductsComponent,
    ConditionsOfContractComponent,
    ViewUserComponent,
    UserListComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule,
    DatePipe,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
