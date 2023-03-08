import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlagService {

  flagUrl = "https://countryflagsapi.com/png";

  constructor(private http: HttpClient) { }

  getFlag(countryCode: string): Observable<any>{
    let httpOptions = {
      headers: new HttpHeaders({
        'Accept': 'image/jpeg',
        'Access-Control-Allow-Origin': 'http://*:4200'
      }),
      responseType: 'blob' as 'json'
    };
    return this.http.get<Observable<Blob>>(`${this.flagUrl}/${countryCode}`, httpOptions);
  }
}
