import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../material.module';
import { ApiService } from '../../services/api.service';
import { Cliente } from '../../models/api.models';
import { Observable, Subscription } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-cliente-info',
  standalone: true,
  imports: [CommonModule, MaterialModule, FormsModule],
  templateUrl: './cliente-info.component.html',
  styleUrls: ['./cliente-info.component.css']
})
export class ClienteInfoComponent implements OnInit {
  public cliente$: Observable<Cliente | null>;
  public emailEdit: string = '';
  public originalEmail: string = '';

  constructor(private apiService: ApiService, private snackBar: MatSnackBar) {
    this.cliente$ = this.apiService.cliente$;
  }

  ngOnInit(): void {
    this.cliente$.subscribe(cliente => {
      if (cliente) {
        this.emailEdit = cliente.email;
        this.originalEmail = cliente.email;
      }
    });
  }
  
  guardarEmail(): void {
    if (this.emailEdit !== this.originalEmail && this.emailEdit) {
      this.apiService.updateEmail(this.emailEdit).subscribe({
        next: (res) => this.snackBar.open(res.message, 'Cerrar', { duration: 3000 }),
        error: (err) => this.snackBar.open(err.error.message, 'Cerrar', { duration: 5000 })
      });
    }
  }

  cancelarTodo(): void {
    if (confirm('¿Estás seguro de que deseas cancelar todas tus suscripciones?')) {
      this.apiService.cancelarTodo().subscribe({
        next: (res) => this.snackBar.open(res.message, 'Cerrar', { duration: 3000 }),
        error: (err) => this.snackBar.open(err.error.message, 'Cerrar', { duration: 5000 })
      });
    }
  }
}