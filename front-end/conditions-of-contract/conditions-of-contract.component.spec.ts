import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConditionsOfContractComponent } from './conditions-of-contract.component';

describe('ConditionsOfContractComponent', () => {
  let component: ConditionsOfContractComponent;
  let fixture: ComponentFixture<ConditionsOfContractComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConditionsOfContractComponent]
    });
    fixture = TestBed.createComponent(ConditionsOfContractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
