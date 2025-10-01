export interface ApiResponse<T> {
  data: T;
  httpCode: number;
  message: string;
}

export interface Fondo {
  id: number;
  nombre: string;
  montoMinimo: number;
  categoria: string;
}

export interface Cliente {
  id: string;
  saldo: number;
  fondosSuscritos: number[];
  email: string; 
}

export interface Transaccion {
  id: string;
  tipo: 'APERTURA' | 'CANCELACION';
  idFondo: number;
  nombreFondo: string;
  monto: number;
  fecha: string; 
}

export interface SuscripcionRequest {
    idFondo: number;
    tipoNotificacion: 'email' | 'sms';
}