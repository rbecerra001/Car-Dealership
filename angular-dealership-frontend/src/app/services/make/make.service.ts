import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

const herokuUrl = 'http://localhost:9092';

@Injectable({
  providedIn: 'root'
})

export class MakeService {

  constructor(private http: HttpClient) { }

  getMakes(): any {
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .get(`${herokuUrl}/makes`, requestOptions);
  }

  getModels(): any {
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .get(`${herokuUrl}/models/`, requestOptions);
  }

  createMake(newMake): any {
    console.log(newMake);
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .post(`${herokuUrl}/makes/`, newMake, requestOptions);
  }

  getMake(makeId): any {
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .get(`${herokuUrl}/makes/${makeId}`, requestOptions);
  }

  getModel(modelId): any {
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .get(`${herokuUrl}/models/${modelId}`, requestOptions);
  }

  createModel(make, newModel): any {
    console.log('service: ', make, newModel);
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .post(`${herokuUrl}/makes/${make.id}/models`, newModel, requestOptions);
  }

  deleteMake(make): any {
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .delete(`${herokuUrl}/makes/${make.id}`, requestOptions);
  }

  deleteModel(make, modelId): any {
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .delete(`${herokuUrl}/makes/${make.id}/models/${modelId}`, requestOptions);
  }
}
