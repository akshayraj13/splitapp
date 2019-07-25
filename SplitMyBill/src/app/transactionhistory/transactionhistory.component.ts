import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { Transaction } from '../models/transaction';

@Component({
  selector: 'app-transactionhistory',
  templateUrl: './transactionhistory.component.html',
  styleUrls: ['./transactionhistory.component.css']
})
export class TransactionhistoryComponent implements OnInit {

  getcredittransaction: Transaction;
  getdebittransaction: Transaction;

  constructor(private httpClientService: HttpClientService) { }

  ngOnInit() {
    this.getCreditTransaction();
    this.getDebitTransaction();
  }

  public getCreditTransaction() {
    this.httpClientService.getCreditTransaction(sessionStorage.getItem('email')).then(getcredittransaction => {
    this.getcredittransaction = getcredittransaction;
  }).catch(err => {
    console.log("Something went wrong, unable to fetch credit transactions");
    console.log(err);

  });
  }


  public getDebitTransaction() {
    this.httpClientService.getDebitTransaction(sessionStorage.getItem('email')).then(getdebittransaction => {
    this.getdebittransaction = getdebittransaction;
  }).catch(err => {
    console.log("Something went wrong,unable to fetch debit transactions");
    console.log(err);

  });
  }

}
