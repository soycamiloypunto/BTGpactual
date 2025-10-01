import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransaccionesList } from './transacciones-list';

describe('TransaccionesList', () => {
  let component: TransaccionesList;
  let fixture: ComponentFixture<TransaccionesList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TransaccionesList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransaccionesList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
