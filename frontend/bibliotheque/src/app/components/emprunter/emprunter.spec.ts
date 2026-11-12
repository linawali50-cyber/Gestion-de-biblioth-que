import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Emprunter } from './emprunter';

describe('Emprunter', () => {
  let component: Emprunter;
  let fixture: ComponentFixture<Emprunter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Emprunter]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Emprunter);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
