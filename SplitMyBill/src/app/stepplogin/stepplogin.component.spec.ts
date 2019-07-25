import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StepploginComponent } from './stepplogin.component';

describe('StepploginComponent', () => {
  let component: StepploginComponent;
  let fixture: ComponentFixture<StepploginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StepploginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StepploginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
