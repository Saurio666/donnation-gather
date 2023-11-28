import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable, Subscription } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { ApiServicioComponent } from '../servicio/apiservicio.component';

@Component({
  selector: 'app-donnation',
  templateUrl: './donnation.component.html',
  styleUrls: ['./donnation.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class DonnationComponent implements OnInit {
  respuestaServicio: any;

  respuestaEventos: any;
  respuestaPuntosEntrega: any;
  responsablePuntoEntrega: any;

  token: any;
  imprimirLog: boolean = true;

  parametrosUsuario: { [key: string]: string } = {};
  validaciones: { [key: string]: string } = {};

  itemsDonacion: {cantidad: number; descripcion: string; tmpId: number}[] =[];

  constructor(
    private route: ActivatedRoute,
    private httpClient: HttpClient,
    private apiServicio: ApiServicioComponent
  ) { }

  ngOnInit(): void {
    this.token = this.route.snapshot.paramMap.get('token');

    this.CargarCatalogos();
  }

  private CargarCatalogos() {
    this.log('token: ' + this.token);
    this.apiServicio.obtenerEventos(this.token).subscribe(r => {
      this.respuestaEventos = r;
    });

    this.apiServicio.obtenerPuntosEntrega(this.token).subscribe(r => {
      this.respuestaPuntosEntrega = r;
    });

    //tmp
    this.itemsDonacion.push({cantidad: 5, descripcion: 'Libras arroz', tmpId: 1});
    this.itemsDonacion.push({cantidad: 2, descripcion: 'Libras azucar', tmpId: 2});
    this.itemsDonacion.push({cantidad: 3, descripcion: 'Docenas huevos', tmpId: 3});
  }

  public onPuntoEntregaSelected(event: any) {
    this.parametrosUsuario['punto_entrega'] = '';
    if (event.target.value) {
      let codigo: number = parseInt(event.target.value);
      this.parametrosUsuario['punto_entrega'] = event.target.value;

      this.respuestaPuntosEntrega?.datos?.puntosEntrega.forEach((pe: { codigoPuntoEntrega: number, responsablePuntoEntrega: string }) => {
        if (pe.codigoPuntoEntrega === codigo) {
          this.responsablePuntoEntrega = pe.responsablePuntoEntrega;
          return;
        }
      });
    } else {
      this.responsablePuntoEntrega = '';
    }
  }

  public onEventoSelected(event: any) {
    this.parametrosUsuario['evento'] = '';
    if (event.target.value) {
      this.parametrosUsuario['evento'] = event.target.value;
    }
  }

  private validarDatos() : boolean {
    this.validaciones = {};
    if(!this.parametrosUsuario['identificacion']) {
      this.validaciones['identificacion'] = 'N';
    }
    if(!this.parametrosUsuario['nombre']) {
      this.validaciones['nombre'] = 'N';
    }
    if(!this.parametrosUsuario['evento']) {
      this.validaciones['evento'] = 'N';
    }
    if(!this.parametrosUsuario['punto_entrega']) {
      this.validaciones['punto_entrega'] = 'N';
    }
    if(!this.parametrosUsuario['boleto']) {
      this.validaciones['boleto'] = 'N';
    }

    return Object.keys(this.validaciones).length <= 0 ;
  }

  public registrarDonacion() {
    this.log( this.parametrosUsuario);

    if(this.validarDatos()) {
      this.log('Se graba');
    } else {
      this.log('NO se graba');
      this.validaciones['general'] = 'N';
    }
  }

  public eliminarElemento(id: any) {
    this.log('id ' + id);
  }



  private log(datos: any) {
    if (this.imprimirLog) {
      console.log('[DonnationComponent]', datos);
    }
  }
}
