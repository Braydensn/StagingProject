import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userUrl: string = `${environment.baseUrl}/user`;
  currentUser?: {id: number, country: string};

  constructor(private http: HttpClient, private cookies: CookieService) { }

  login(email: string, password: string): Observable<User>{
    let payload = {email: email, password: password};
    return this.http.post<any>(`${this.userUrl}/login`, JSON.stringify(payload), {headers: environment.headers, withCredentials: environment.withCredentials});
  }

  register(email: string, password: string, firstName: string, lastName: string, country: string): Observable<User>{
    let payload = {email: email, password: password, firstName: firstName, lastName: lastName, country: country};
    return this.http.post<any>(`${this.userUrl}/register`, JSON.stringify(payload), {headers: environment.headers, withCredentials: environment.withCredentials});
  }

  getCurrentUser(){
    if(this.cookies.check("user")){
      let cookieData: string[] = this.cookies.get("user").split("-");
      this.currentUser = {id: parseInt(cookieData[0]), country: cookieData[1]};
    }
  }

}
