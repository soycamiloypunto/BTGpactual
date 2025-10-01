import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material.module';
import { ClienteInfoComponent } from './components/cliente-info/cliente-info.component';
import { FondosListComponent } from './components/fondos-list/fondos-list.component';
import { TransaccionesListComponent } from './components/transacciones-list/transacciones-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    MaterialModule, 
    ClienteInfoComponent,
    FondosListComponent,
    TransaccionesListComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'btg-frontend';
}