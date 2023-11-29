import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable, Inject, OnInit } from '@angular/core';
import { DOCUMENT } from '@angular/common';

import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})

export class ApiServicioComponent {
    constructor(
        private httpClient: HttpClient,
        @Inject(DOCUMENT) private document: any
    ) { }

    public getApiUrl(): string {
        console.log("Port: ", this.document.location.Port);
        console.log("Port: ", window.location.port);
        if(window.location.port) {
            return window.location.protocol + "//" + this.document.location.hostname + ":8290/api";
        }

        return window.location.protocol + "//" + this.document.location.hostname + "/api";
    }

    public obtenerEventos(token: string): Observable<any> {
        return this.httpClient.get<any>(`${this.getApiUrl()}/catalogo/evento/${token}`).pipe(
            catchError(this.handleError('obtenerEvento', { message: `No se puede obtener el catalogo de eventos, token ${token}` }))
        );
    }

    public obtenerPuntosEntrega(token: string): Observable<any> {
        return this.httpClient.get<any>(`${this.getApiUrl()}/catalogo/puntos_entrega/${token}`).pipe(
            catchError(this.handleError('obtenerPuntosEntrega', { message: `No se puede obtener el catalogo de puntos de entrega, token ${token}` }))
        );
    }

    public registrarDonacion(data: any): Observable<any> {
        const headers = new HttpHeaders({
            'Content-Type': 'application/json'
        });
        return this.httpClient.post(`${this.getApiUrl()}/donacion_personal/registrar`, data, { headers }).pipe(
            catchError(this.handleError('registrarDonacion', { message: `No se puede registrar la donacion ${data}` }))
        );
    }

    private handleError(operation = 'operation', result: any) {
        return (error: any): Observable<any> => {
            if (error instanceof HttpErrorResponse) {
                if (error.error && error.error.message) {
                    return of({ message: error.error.message });
                }
            }

            console.error(operation);
            console.error(error);

            return of(result);
        };
    }
}