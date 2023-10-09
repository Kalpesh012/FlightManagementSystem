import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecognitionsComponent } from './recognitions.component';

describe('RecognitionsComponent', () => {
  let component: RecognitionsComponent;
  let fixture: ComponentFixture<RecognitionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RecognitionsComponent]
    });
    fixture = TestBed.createComponent(RecognitionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
