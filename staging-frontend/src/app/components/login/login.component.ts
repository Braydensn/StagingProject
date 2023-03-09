import { Component } from '@angular/core';
import { FormBuilder, UntypedFormControl, UntypedFormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  error: boolean = false;

  loginForm = new UntypedFormGroup({
    email: new UntypedFormControl(''),
    password: new UntypedFormControl('')
  });

  constructor(private userService: UserService, private router: Router) {}


  onSubmit(){
    this.userService.login(this.loginForm.get("email")?.value, this.loginForm.get("password")?.value).subscribe(
      (user) => {this.userService.currentUser = {id: user.id!, country: user.country!}; this.userService.getCurrentUser(); console.log(this.userService.currentUser);},
      (err) => {console.log(err); this.error = true;},
      () => this.router.navigate(["home"])
    );
  }
}
