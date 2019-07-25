import { FormGroup, FormControl, Validators } from '@angular/forms'
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'angularx-social-login';
import { SocialUser } from 'angularx-social-login';
import { Card } from '../models/card';
import { UserService } from '../service/user.service';
import { User } from '../models/user';
import { HttpClientService } from '../service/http-client.service';


@Component({
  selector: 'app-addcard',
  templateUrl: './addcard.component.html',
  styleUrls: ['./addcard.component.css']
})

export class AddcardComponent implements OnInit {

  userNew: User;
  user: SocialUser;
  card: Card;
  msg: any;
  email: string;



  constructor(private authService: AuthService,
    private httpClientService: HttpClientService,
    private userService: UserService
  ) { }

  

  ngOnInit() {
      this.email = sessionStorage.getItem('email');
  }


  addCardForm = new FormGroup({
    cardholdername: new FormControl('', [Validators.pattern("^[A-Za-z' ']{5,30}$"), Validators.required]),
    cardnumber: new FormControl('', [Validators.pattern('[0-9]\\d{15}'), Validators.required]),
    cardname: new FormControl('', [Validators.required]),
    month: new FormControl('', [Validators.required]),
    year: new FormControl('', [Validators.required]),
    cvv: new FormControl('', [Validators.pattern('[0-9]\\d{2}'), Validators.required]),
    email: new FormControl()
  });


  onSubmit(): void {
    this.addCardForm.patchValue({
      email: this.email
    });
    console.log(this.addCardForm.value);
    this.httpClientService.addCard(this.addCardForm.value).then(data => {
      this.httpClientService.addUser().subscribe(user => this.userNew = user);
      this.userService.setDisplayNavbar(true);
      alert("Card Added Successfully");
    }).catch(err => {
      console.log("Something went wrong");
      console.log(err);
      if (err.status === 400)
        alert("Invalid Credentials");
      else
        alert("Something went wrong.");

    });

    this.addCardForm.reset({ month: '', year: '' });
  }
}









