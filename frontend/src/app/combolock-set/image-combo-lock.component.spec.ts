import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageComboLockComponent } from './image-combo-lock.component';

describe('ImageComboLockComponent', () => {
  let component: ImageComboLockComponent;
  let fixture: ComponentFixture<ImageComboLockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImageComboLockComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImageComboLockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
