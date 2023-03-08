import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productUrl: string = `${environment.baseUrl}/product`;

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<Product[]>{
    return this.http.get<Product[]>(this.productUrl, {headers: environment.headers, withCredentials: environment.withCredentials});
  }

  getProduct(id: number): Observable<Product>{
    return this.http.get<Product>(`${this.productUrl}/${id}`, {headers: environment.headers, withCredentials: environment.withCredentials});
  }

}
