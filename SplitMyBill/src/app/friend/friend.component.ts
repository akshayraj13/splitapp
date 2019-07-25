import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { HttpClientService } from '../service/http-client.service';


@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.css']
})

export class FriendComponent implements OnInit {
  user: User;

  constructor(
    private httpClientService: HttpClientService
  ) { }

  ngOnInit() {
    this.httpClientService.getFriendByEmail().subscribe(user => {
      this.user = user;
    });
  }
}


