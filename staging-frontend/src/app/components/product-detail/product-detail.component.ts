import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent {
  product!: Product;

  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router){}

  ngOnInit(){
    const productId = Number(this.route.snapshot.paramMap.get("id"));
    this.productService.getProduct(productId).subscribe(
      (result) => this.product = result
    );
  }

  addToCart(event: any, quantity: string){
    this.productService.addToCart(this.product, Number(quantity));
    event.target.disabled = true;
    event.target.classList.remove("btn-warning");
    event.target.classList.add("btn-success");
    event.target.innerText = "Added to cart"

  }
}
