import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../material.module';
import { ApiService } from '../../services/api.service';
import { Cliente, Fondo, SuscripcionRequest } from '../../models/api.models';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-fondos-list',
  standalone: true,
  imports: [CommonModule, MaterialModule, FormsModule],
  templateUrl: './fondos-list.component.html',
  styleUrls: ['./fondos-list.component.css']
})
export class FondosListComponent implements OnInit {
  fondos: Fondo[] | null = null;
  public cliente$: Observable<Cliente | null>;

  public tipoNotificacion: 'email' | 'sms' = 'email';

  constructor(private apiService: ApiService, private snackBar: MatSnackBar) {
    this.cliente$ = this.apiService.cliente$;
  }

  ngOnInit(): void {
    this.apiService.getFondos().subscribe(response => {
      this.fondos = response.data;
    });
  }

  estaSuscrito(cliente: Cliente | null, fondoId: number): boolean {
    return cliente?.fondosSuscritos.includes(fondoId) ?? false;
  }

  suscribir(fondoId: number): void {
    const request: SuscripcionRequest = { idFondo: fondoId, tipoNotificacion: this.tipoNotificacion };
    this.apiService.suscribir(request).subscribe({
      next: (res) => this.snackBar.open("Suscripción exitosa. Se envió una notificación.", 'Cerrar', { duration: 3000 }),
      error: (err) => this.snackBar.open(err.error.message, 'Cerrar', { duration: 5000 })
    });
  }

  cancelar(fondoId: number): void {
    const request: SuscripcionRequest = { idFondo: fondoId, tipoNotificacion: 'email' }; 
    this.apiService.cancelar(request).subscribe({
      next: (res) => this.snackBar.open(res.message, 'Cerrar', { duration: 3000 }),
      error: (err) => this.snackBar.open(err.error.message, 'Cerrar', { duration: 5000 })
    });
  }
}