import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'staging-frontend';

  constructor(private cookies: CookieService){}

  checkCookie(){
    console.log(this.cookies.get("user"));
  }
}
