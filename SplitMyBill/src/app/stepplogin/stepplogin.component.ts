import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpClientService } from '../service/http-client.service';

@Component({
  selector: 'app-stepplogin',
  templateUrl: './stepplogin.component.html',
  styleUrls: ['./stepplogin.component.css']
})
export class StepploginComponent implements OnInit {
  userName: string;
  password: string;
  data: any;




  steppLoginForm = new FormGroup({
    userName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),

  });

  constructor(
    public httpClientService: HttpClientService,
 
  ) {
  }
  ngOnInit() {
  }


  onSubmit(): void {

    console.log('you clicked login');
    console.log(this.steppLoginForm.value);
    this.httpClientService.steppLogin(this.steppLoginForm.value).then(data => {
    alert(data)
      //      alert('Logged-in Successfully')
    }).catch(err => {
      console.log("Something went wrong");
      console.log(err);
      if (err.status === 501)
      {
        console.log(err);
        alert("Username/Password was left empty");
      }
      else
        alert("Something went wrong.");

    });
    this.steppLoginForm.reset({ userName: '', password: '' });
  }
}
