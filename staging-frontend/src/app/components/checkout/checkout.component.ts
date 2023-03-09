import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent {

  constructor(private productService: ProductService, private router: Router){}

  getTotal(){
    return this.productService.getTotalCartCost();
  }

  pay(event: any){
    event.target.classList.remove("btn-warning");
    event.target.classList.add("btn-success");
    event.target.innerText = "Payment successful";

    this.productService.emptyCart();

    setTimeout(this.backToCart.bind(this), 1500);
  }

  backToCart(){
    this.router.navigate(["cart"]);
  }

}
