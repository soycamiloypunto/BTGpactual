import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FondosList } from './fondos-list';

describe('FondosList', () => {
  let component: FondosList;
  let fixture: ComponentFixture<FondosList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FondosList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FondosList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
