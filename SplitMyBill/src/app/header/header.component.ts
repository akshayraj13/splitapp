import { Component, OnInit } from '@angular/core';
import { AuthService, GoogleLoginProvider } from 'angularx-social-login';
import { Router } from '@angular/router';
import { SocialUser } from 'angularx-social-login';
import { UserService } from '../service/user.service';
import { AuthenticationService } from '../authentication.service';
import { User } from '../models/user';
import { HttpClientService } from '../service/http-client.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  user: User;
  socialUser: SocialUser;
  name: string;
  btn: string = "Login";
  user1: User;
  check: any = false;

  constructor(private authService: AuthService,
    private router: Router,
    private httpClientService: HttpClientService,
    private userService: UserService,
    private loginservice: AuthenticationService) {
  }

  ngOnInit() {
    this.authService.authState.subscribe((socialUser) => {
      this.socialUser = socialUser;
      if (socialUser) {
        sessionStorage.setItem('email', socialUser.email);
        sessionStorage.setItem('name', socialUser.name);
        sessionStorage.setItem('fname', socialUser.firstName);
        sessionStorage.setItem('lname', socialUser.lastName);
        sessionStorage.setItem('photoUrl', socialUser.photoUrl);
        this.loginservice.authenticate(this.socialUser.name);
        this.btn = "Logout";
        this.name = socialUser.name; //Welcome title
        
        this.httpClientService.checkCardExist(this.socialUser.email).subscribe(data => {
          this.check = data;

          if (!this.check) {
            this.router.navigate(['addcard']);
          }
          else {
            this.httpClientService.addUser().subscribe(user => {
              this.user = user;
              this.userService.setDisplayNavbar(true);
            });
          }
        });
      }
    })
  }


  signInOut(): void {
    if (this.socialUser != null) {
      this.signOut();
      this.btn = "Login";

    }
    else {
      this.signInWithGoogle();

    }
  }

  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(x => console.log(x))
  }

  signOut(): void {
    this.authService.signOut();
    sessionStorage.clear();
    this.router.navigate(['']);
    this.userService.setDisplayNavbar(false);
  }
  isSessionExists() {
    if (sessionStorage.getItem('name')) {
      return true;
    }
    return false;
  }
}
