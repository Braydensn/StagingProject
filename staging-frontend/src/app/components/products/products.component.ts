import { Component } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {

  products: Product[] = [];

  constructor(private productService: ProductService){}

  ngOnInit(){
    this.productService.getAllProducts().subscribe(
      (products) => this.products = products,
      (err) => console.log(err),
      () => console.log("Products Retrieved")
    );
  }

}
