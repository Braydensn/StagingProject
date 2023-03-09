import { Component, Sanitizer } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { FlagService } from 'src/app/services/flag.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  country: string = "";
  countryImg: any = "";

  constructor(private cookies: CookieService, private router: Router, private flagService: FlagService, private sanitizer: DomSanitizer){}


  ngOnInit(){
    if(this.cookies.check("user")){
      this.country = this.cookies.get("user").split("-")[1];
      this.flagService.getFlag(this.country).subscribe(
        (response) => {let image = URL.createObjectURL(response); this.countryImg = this.sanitizer.bypassSecurityTrustUrl(image)}
      )
    }
  }

  logout(){
    this.cookies.delete("user", "/");
    delete this.flagService.flag;
    this.router.navigate(["login"]);
  }

}
