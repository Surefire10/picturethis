import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvalidroutComponent } from './invalidrout.component';

describe('InvalidroutComponent', () => {
  let component: InvalidroutComponent;
  let fixture: ComponentFixture<InvalidroutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvalidroutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InvalidroutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
