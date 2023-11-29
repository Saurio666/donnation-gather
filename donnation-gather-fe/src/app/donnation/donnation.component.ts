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

  parametrosUsuario: { [key: string]: any } = {};
  validaciones: { [key: string]: string } = {};

  itemsDonacion: { cantidad: number; descripcion: string; tmpId: number }[] = [];

  errorGeneral: any;

  item_accion: string = '';
  itemSeleccionado: any;

  tmpIdCounter: number = 0;

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
      this.log('[obtenerEventos] r ' + r)
      this.errorGeneral = r?.datos?.err_autorizacion;
    });

    this.apiServicio.obtenerPuntosEntrega(this.token).subscribe(r => {
      this.respuestaPuntosEntrega = r;
    });

    //tmp
    /*this.itemsDonacion.push({ cantidad: 5, descripcion: 'Libras arroz', tmpId: this.tmpIdCounter++ });
    this.itemsDonacion.push({ cantidad: 2, descripcion: 'Libras azucar', tmpId: this.tmpIdCounter++ });
    this.itemsDonacion.push({ cantidad: 3, descripcion: 'Docenas huevos', tmpId: this.tmpIdCounter++ });
    this.itemsDonacion.push({ cantidad: 1, descripcion: 'Funda de caramelos', tmpId: this.tmpIdCounter++ });*/
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

  private validarDatos(): boolean {
    this.validaciones = {};
    if (!this.parametrosUsuario['identificacion']) {
      this.validaciones['identificacion'] = 'N';
    }
    if (!this.parametrosUsuario['nombre']) {
      this.validaciones['nombre'] = 'N';
    }
    if (!this.parametrosUsuario['evento']) {
      this.validaciones['evento'] = 'N';
    }
    if (!this.parametrosUsuario['punto_entrega']) {
      this.validaciones['punto_entrega'] = 'N';
    }
    if (!this.parametrosUsuario['boleto']) {
      this.validaciones['boleto'] = 'N';
    }
    if(this.itemsDonacion.length <= 0) {
      this.validaciones['items'] = 'N';
    }

    return Object.keys(this.validaciones).length <= 0;
  }

  public registrarDonacion() {
    this.item_accion = 'N';
    this.log(this.parametrosUsuario);

    if (this.validarDatos()) {
      this.log('Se graba');
      this.parametrosUsuario['items'] = this.itemsDonacion;
      this.parametrosUsuario['token'] = this.token;

      this.apiServicio.registrarDonacion(this.parametrosUsuario).subscribe(r => {
        this.respuestaServicio = r;
        this.log(r);
      });
    } else {
      this.log('NO se graba');
      this.validaciones['general'] = 'N';
    }
  }

  public eliminarElemento(id: any) {
    this.item_accion = 'E';
    this.log('id ' + id);

    const indexOfObject = this.itemsDonacion.findIndex((item) => {
      return item.tmpId === parseInt(id);
    });

    this.log('indexOfObject ' + indexOfObject);

    if (indexOfObject > -1) {
      this.itemsDonacion.splice(indexOfObject, 1)
    }
  }

  public modificarElemento(id: any) {
    this.log('id ' + id);

    const indexOfObject = this.itemsDonacion.findIndex((item) => {
      return item.tmpId === parseInt(id);
    });

    this.log('indexOfObject ' + indexOfObject);

    if (indexOfObject > -1) {
      this.item_accion = 'M';
      this.itemSeleccionado = this.itemsDonacion[indexOfObject];
    }
  }

  public agregarElemento() {
    this.item_accion = 'M';
    this.itemSeleccionado = { cantidad: 0, descripcion: '', tmpId: this.tmpIdCounter++ };
    this.itemsDonacion.push(this.itemSeleccionado);
  }

  public completarEdicionItem() {
    this.item_accion = 'N';
  }
  
  public recargarPagina() {
    window.location.reload();
  }

  private log(datos: any) {
    if (this.imprimirLog) {
      console.log('[DonnationComponent]', datos);
    }
  }
}
