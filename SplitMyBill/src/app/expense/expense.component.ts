import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../models/user';
import { Expense } from '../models/expense';
import { HttpClientService } from '../service/http-client.service';


@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.css']
})

export class ExpenseComponent implements OnInit {

  setexpense: Expense;
  friends = new FormControl();
  selectedFriends: string[];
  expense: number;
  user: User;
  addExpenseForm = new FormGroup({
    selectedFriends: new FormControl('', [Validators.required]),
    expense: new FormControl('', [Validators.pattern('[0-9]\\d{0,9}'), Validators.required])

  });

  constructor(
    private httpClientService: HttpClientService
  ) { }


  ngOnInit() {
    this.httpClientService.getFriendByEmail().subscribe(user => {
      this.user = user;
      this.setexpense = new Expense("", null, 0);
    });
  }


  onSubmit() {
    this.setexpense.expense = this.addExpenseForm.get('expense').value;
    this.setexpense.friend_email = this.addExpenseForm.get('selectedFriends').value;
    this.setexpense.user_email = sessionStorage.getItem('email');
    this.httpClientService.setexpense(this.setexpense).then(data => {
      alert(data);
    }).catch(err => {
      console.log("Something went wrong");
      console.log(err);
      alert("Something went wrong, try again")
    });
    this.addExpenseForm.reset();
  }
}











