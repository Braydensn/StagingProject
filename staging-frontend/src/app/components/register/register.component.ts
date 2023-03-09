import { Component } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { countries } from 'src/app/countriesList';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm = new UntypedFormGroup({
    fname: new UntypedFormControl(''),
    lname: new UntypedFormControl(''),
    email: new UntypedFormControl(''),
    password: new UntypedFormControl(''),
    country: new UntypedFormControl('')
  })

  countries = countries;
  error: boolean = false;

  constructor(private userService: UserService, private router: Router){}

  onSubmit(){
    this.userService.register(this.registerForm.get('email')?.value, this.registerForm.get('password')?.value, 
      this.registerForm.get('fname')?.value, this.registerForm.get('lname')?.value, this.registerForm.get('country')?.value).subscribe(
        () => console.log("New user registered"),
        (err) => {console.log(err); this.error = true},
        () => this.router.navigate(['login'])
    );
  }

}
