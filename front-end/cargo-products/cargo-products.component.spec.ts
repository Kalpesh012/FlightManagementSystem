import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CargoProductsComponent } from './cargo-products.component';

describe('CargoProductsComponent', () => {
  let component: CargoProductsComponent;
  let fixture: ComponentFixture<CargoProductsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CargoProductsComponent]
    });
    fixture = TestBed.createComponent(CargoProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
