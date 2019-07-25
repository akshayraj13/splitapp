import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {

  email: string = sessionStorage.getItem('email');
  photoUrl: string = sessionStorage.getItem('photoUrl');
  name: string = sessionStorage.getItem('name');


  constructor() { }

  ngOnInit() {
  }

}
