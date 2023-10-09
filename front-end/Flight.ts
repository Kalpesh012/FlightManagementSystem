import { Airport } from "./Airport";
import { Company } from "./Company";
export class Flight {
  ownerCompanyName():Company {
      throw new Error('Method not implemented.');
    }
    airport():Airport{
      throw new Error('Method not implemented.');
    }
    recordNumber : number = 0;
    isCommercial : boolean = false;
    flightName : string = "";
    flightCode : string = "";
    source : string = "";
    destination : string = "";
    capacity : number = 0;
    ticketCost : number = 0;
    flightRating : number = 0;
    availableSeats : number = 0;
   }