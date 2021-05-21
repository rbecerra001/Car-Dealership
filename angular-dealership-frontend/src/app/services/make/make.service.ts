import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

const herokuUrl = 'https://damp-bayou-38809.herokuapp.com';

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
      .get(`${herokuUrl}/api/makes`, requestOptions);
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
      .post(`${herokuUrl}/api/makes/`, newMake, requestOptions);
  }

  getMake(makeId): any {
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .get(`${herokuUrl}/api/makes/${makeId}`, requestOptions);
  }

  getModel(modelId): any {
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .get(`${herokuUrl}/api/models/${modelId}`, requestOptions);
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
      .post(`${herokuUrl}/api/makes/${make.id}/models`, newModel, requestOptions);
  }

  deleteMake(make): any {
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .delete(`${herokuUrl}/api/makes/${make.id}`, requestOptions);
  }

  deleteModel(make, modelId): any {
    const token = localStorage.getItem('token');
    const requestOptions = {
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      }),
    };
    return this.http
      .delete(`${herokuUrl}/api/makes/${make.id}/models/${modelId}`, requestOptions);
  }
}
