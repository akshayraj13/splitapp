import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SocialUser } from 'angularx-social-login';
import { Observable } from 'rxjs';
import { Card } from '../models/card';
import { User } from '../models/user';
import { Transaction } from '../models/transaction';
import { Settlement } from '../models/settlement';
import { Expense } from '../models/expense';
import { PaymentDetail } from '../models/paymentDetail';
import { SteppLogin } from '../models/stepplogin';


@Injectable({
  providedIn: 'root'
})
export class HttpClientService {
  user: SocialUser;
  url: String = 'http://localhost:8085/';
  constructor(
    private httpClient: HttpClient
  ) {
  }


  getCardByEmail() {
    console.log("test call for card by email");
    return this.httpClient.get<Card>(this.url + 'card/' + sessionStorage.getItem('email'));
  }

  getFriendByEmail() {
    console.log("test call for friend by email");
    return this.httpClient.get<User>(this.url + 'friend/' + sessionStorage.getItem('email'));
  }


  getSettlement() {
    console.log("test call for getSettlement");
    return this.httpClient.get<Settlement>(this.url + 'settlement/' + sessionStorage.getItem('email'));
  }

  getCreditTransaction(user_email: String) {
    console.log("test call for getCreditTransaction");
    return this.httpClient.get<Transaction>(this.url + 'credittransaction/' + user_email).toPromise();
  }

  getDebitTransaction(user_email: String) {
    console.log("test call for getCreditTransaction");
    return this.httpClient.get<Transaction>(this.url + 'debittransaction/' + user_email).toPromise();
  }


  setexpense(setexpense: Expense) {
    console.log("test call for setexpense");
    return this.httpClient.post(this.url + 'expense', setexpense, { responseType: 'text' }).toPromise();
  }

  doPayment(paymentdetail: PaymentDetail) {
    console.log("Payment detail:  " + paymentdetail.friend_email);
    return this.httpClient.post(this.url + 'payment', paymentdetail, { responseType: 'text' }).toPromise();
  }

  public sendEmail(receiver_user_email) {
    console.log("test call for sending email");

    return this.httpClient.get(this.url + 'sendemail/' + sessionStorage.getItem('email') + '/' + receiver_user_email, { responseType: 'text' }).toPromise();
  }

  public sendReminder(receiver_user_email) {
    console.log("test call for sending reminder");

    return this.httpClient.get(this.url + 'sendreminder/' + sessionStorage.getItem('email') + '/' + receiver_user_email, { responseType: 'text' }).toPromise();
  }

  checkCardExist(email: string): Observable<boolean> {
    console.log("test call for checkCardExist");

    return this.httpClient.get<boolean>(this.url + 'checkcardexist/' + email)
  }

  public addCard(card: Card) {
    console.log("test call for addCard");
    return this.httpClient.post(this.url + 'card', card, { responseType: 'text' }).toPromise();
  }


  public addUser() {
    console.log("test call for addUser");
    return this.httpClient.get<User>(this.url + 'adduser/' + sessionStorage.getItem('email') + '/' + sessionStorage.getItem('fname') + '/' + sessionStorage.getItem('lname'));
  }



public steppLogin(stepplogin: SteppLogin) {
  console.log('test call for login');
  return this.httpClient.post('http://localhost:8080/' + 'stepps/user/login', stepplogin,{ responseType: 'text' }).toPromise();
}


  ngOnInit() {
  }

}

