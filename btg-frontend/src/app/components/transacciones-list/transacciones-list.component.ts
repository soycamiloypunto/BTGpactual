import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../material.module';
import { ApiService } from '../../services/api.service';
import { Transaccion } from '../../models/api.models';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-transacciones-list',
  standalone: true,
  imports: [CommonModule, MaterialModule],
  templateUrl: './transacciones-list.component.html',
  styleUrls: ['./transacciones-list.component.css']
})
export class TransaccionesListComponent implements OnInit, OnDestroy {
  transacciones: Transaccion[] | null = null;
  private clienteSubscription!: Subscription;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.clienteSubscription = this.apiService.cliente$.subscribe(cliente => {
      if (cliente) {
        this.loadTransacciones();
      }
    });
  }

  ngOnDestroy(): void {
    if (this.clienteSubscription) {
      this.clienteSubscription.unsubscribe();
    }
  }

  loadTransacciones(): void {
    this.apiService.getTransacciones().subscribe(response => {
      this.transacciones = response.data;
    });
  }
}