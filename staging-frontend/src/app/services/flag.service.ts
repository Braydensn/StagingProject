import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlagService {

  flagUrl = "https://countryflagsapi.com/png";

  flag?: Observable<Blob>;

  constructor(private http: HttpClient) { }

  getFlag(countryCode: string): Observable<any>{
    if(this.flag == undefined){
      let httpOptions = {
        headers: new HttpHeaders({
          'Accept': 'image/jpeg',
          'Access-Control-Allow-Origin': 'http://*:4200'
        }),
        responseType: 'blob' as 'json'
      };
      this.flag = this.http.get<Blob>(`${this.flagUrl}/${countryCode}`, httpOptions);
      return this.flag;
    }else{
      return this.flag
    }
  }
}
