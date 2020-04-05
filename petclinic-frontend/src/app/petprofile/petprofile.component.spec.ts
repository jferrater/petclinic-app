import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PetprofileComponent } from './petprofile.component';

describe('PetprofileComponent', () => {
  let component: PetprofileComponent;
  let fixture: ComponentFixture<PetprofileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PetprofileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PetprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
