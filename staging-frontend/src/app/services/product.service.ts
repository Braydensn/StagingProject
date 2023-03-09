import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../models/product';
import { CartProduct } from '../models/cart-product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  cart: CartProduct[] = [];

  productUrl: string = `${environment.baseUrl}/product`;

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<Product[]>{
    return this.http.get<Product[]>(this.productUrl, {headers: environment.headers, withCredentials: environment.withCredentials});
  }

  getProduct(id: number): Observable<Product>{
    return this.http.get<Product>(`${this.productUrl}/${id}`, {headers: environment.headers, withCredentials: environment.withCredentials});
  }

  addToCart(product: Product, quantity: number){
    for(let cartProduct of this.cart){
      if(cartProduct.product.id == product.id){
        cartProduct.quantity += quantity;
        return;
      }
    }
    this.cart.push(new CartProduct(product, quantity));
  }

  getTotalCartCost(){
    let total = 0;
    for(let product of this.cart){
      total += product.quantity * product.product.price;
    }

    return total;
  }

  emptyCart(){
    this.cart = [];
  }

}
