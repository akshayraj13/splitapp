import { Component, OnInit } from '@angular/core';
import { AuthService } from 'angularx-social-login';
import { HttpClientService } from '../service/http-client.service';
import { UserService } from '../service/user.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: []
})

export class NavbarComponent implements OnInit {
  check: any = false;
  public displayNavbarSubscription: Subscription;
  
  constructor(private authService: AuthService,
    private httpClientService: HttpClientService,
    private userService: UserService
  ) {
    this.displayNavbarSubscription = this.userService.dispalyEventEmitter.subscribe((value) => {
      this.check = value;
    });
  }

  ngOnInit() {

  }

  isSessionExists() {
    if (sessionStorage.getItem('name') && this.check) {
      return true;
    }
    return false;
  }
}