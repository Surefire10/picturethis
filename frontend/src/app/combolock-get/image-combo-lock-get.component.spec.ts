import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageComboLockGetComponent } from './image-combo-lock-get.component';

describe('ImageComboLockGetComponent', () => {
  let component: ImageComboLockGetComponent;
  let fixture: ComponentFixture<ImageComboLockGetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImageComboLockGetComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImageComboLockGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
