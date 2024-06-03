import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BaseService {
  protected baseUrl: string = 'http://localhost:4200/api/v1'; // Replace with your API URL

  constructor(private http: HttpClient) {}

  protected get<T>(
    url: string,
    params?: HttpParams,
    headers?: HttpHeaders,
  ): Observable<T> {
    const options = { params, headers };
    return this.http.get<T>(`${this.baseUrl}/${url}`, options);
  }

  protected post<T>(
    url: string,
    body?: any,
    headers?: HttpHeaders,
  ): Observable<T> {
    const options = { headers };
    return this.http.post<T>(`${this.baseUrl}/${url}`, body, options);
  }

  protected put<T>(
    url: string,
    body?: any,
    headers?: HttpHeaders,
  ): Observable<T> {
    const options = { headers };
    return this.http.put<T>(`${this.baseUrl}/${url}`, body, options);
  }

  protected delete<T>(url: string, headers?: HttpHeaders): Observable<T> {
    const options = { headers };
    return this.http.delete<T>(`${this.baseUrl}/${url}`, options);
  }
}
