import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-addfriend',
  templateUrl: './addfriend.component.html',
  styleUrls: ['./addfriend.component.css']
})

export class AddfriendComponent implements OnInit {

  msg: string = '';

  constructor(private httpClientService: HttpClientService) { }

  ngOnInit() {
  }

  sendEmailForm = new FormGroup({
    receiver_user_email: new FormControl('', [Validators.email, Validators.required])
  });


  get receiver_user_email() {
    return this.sendEmailForm.get('receiver_user_email');
  }

  onSubmit() {
    this.httpClientService.sendEmail(this.sendEmailForm.get('receiver_user_email').value).then(data => {
      this.msg = data;
    }).catch(err => {
      console.log("Something went wrong");
      console.log(err);
      if (err.status === 400)
        this.msg = "Sending request to yourself?";
      else
        this.msg = "Something went wrong.";
    });
    this.sendEmailForm.reset();
  }

}
