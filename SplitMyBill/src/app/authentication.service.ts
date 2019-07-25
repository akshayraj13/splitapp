import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  authenticate(username) {
    sessionStorage.setItem('username', username)
    return true;
  }
  userlogincheck() {
    let user = sessionStorage.getItem('username')
    return !(user === null)
  }
  logout() {
    sessionStorage.removeItem('username')
  }
}
