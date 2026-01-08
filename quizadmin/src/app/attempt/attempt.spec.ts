import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Attempt } from './attempt';

describe('Attempt', () => {
  let component: Attempt;
  let fixture: ComponentFixture<Attempt>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Attempt]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Attempt);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
