import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CartProduct } from 'src/app/models/cart-product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {
  cartProducts : CartProduct[] = [];

  constructor(private productService: ProductService, private router: Router){}

  ngOnInit(){
    this.cartProducts = this.productService.cart;
  }

  getTotalCost(){
    return this.productService.getTotalCartCost();
  }
}
