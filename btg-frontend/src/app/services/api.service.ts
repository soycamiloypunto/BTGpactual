import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { ApiResponse, Cliente, Fondo, SuscripcionRequest, Transaccion } from '../models/api.models';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly API_URL = 'http://localhost:8080/api';
  
  private clienteState = new BehaviorSubject<Cliente | null>(null);
  public cliente$ = this.clienteState.asObservable();

  constructor(private http: HttpClient) {
    this.refreshClienteData();
  }

  refreshClienteData(): void {
    this.http.get<ApiResponse<Cliente>>(`${this.API_URL}/cliente`)
      .subscribe(response => {
        this.clienteState.next(response.data); 
      });
  }
  
  updateEmail(email: string): Observable<ApiResponse<any>> {
    return this.http.put<ApiResponse<any>>(`${this.API_URL}/cliente/email`, { email }).pipe(
      tap(() => this.refreshClienteData()) 
    );
  }

  suscribir(request: SuscripcionRequest): Observable<ApiResponse<any>> {
    return this.http.post<ApiResponse<any>>(`${this.API_URL}/suscripciones`, request).pipe(
      tap(() => this.refreshClienteData())
    );
  }

  cancelar(request: SuscripcionRequest): Observable<ApiResponse<any>> {
    return this.http.post<ApiResponse<any>>(`${this.API_URL}/cancelaciones`, request).pipe(
      tap(() => this.refreshClienteData())
    );
  }

  cancelarTodo(): Observable<ApiResponse<any>> {
    return this.http.post<ApiResponse<any>>(`${this.API_URL}/cancelaciones/todas`, {}).pipe(
      tap(() => this.refreshClienteData())
    );
  }
  
  getFondos(): Observable<ApiResponse<Fondo[]>> {
    return this.http.get<ApiResponse<Fondo[]>>(`${this.API_URL}/fondos`);
  }

  getTransacciones(): Observable<ApiResponse<Transaccion[]>> {
    return this.http.get<ApiResponse<Transaccion[]>>(`${this.API_URL}/transacciones`);
  }
}