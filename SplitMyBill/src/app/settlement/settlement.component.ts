import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { Card } from '../models/card';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Settlement } from '../models/settlement';
import { PaymentDetail } from '../models/paymentDetail';
import {  HttpClientService } from '../service/http-client.service';


@Component({
  selector: 'app-settlement',
  templateUrl: './settlement.component.html',
  styleUrls: ['./settlement.component.css']
})
export class SettlementComponent implements OnInit {

  paymentdetail: PaymentDetail;
  email: string;
  cardname: string;
  cardnumber: number;
  cvv: number;
  name: string;
  settlement: Settlement;
  modalRef: BsModalRef;
  card: Card;
  status: Boolean = true;
  billtopay: number;


  constructor(
    private httpClientService: HttpClientService,
    private modalService: BsModalService
  ) { }

  ngOnInit() {
    this.getSettlements();
  }


  paymentForm = new FormGroup({
    selectedCard: new FormControl('', [Validators.required]),
    cvv: new FormControl('', [Validators.pattern('[0-9]\\d{2}'), Validators.required])
  });


  public getSettlements() {

    this.httpClientService.getSettlement().subscribe(settlement => {
      this.settlement = settlement;
    });

    this.httpClientService.getCardByEmail().subscribe(card => {
      this.card = card;
    });
    this.paymentdetail = new PaymentDetail(0, 0, "", 0, "");
  }



  openModal(template: TemplateRef<any>, email: string, billtopay: number) {
    this.modalRef = this.modalService.show(template);
    this.email = email;
    this.billtopay = billtopay;
  }


  onSubmit(friend_email: string, billtopay: number) {
    
    this.paymentdetail.cardnumber = this.paymentForm.get('selectedCard').value;
    this.paymentdetail.cvv = this.paymentForm.get('cvv').value;
    this.paymentdetail.friend_email = friend_email;
    this.paymentdetail.billtopay = billtopay;
    this.paymentdetail.user_email = sessionStorage.getItem('email');

    this.httpClientService.doPayment(this.paymentdetail).then(data => {
      alert(data);
      if (this.modalRef) {
        this.modalRef.hide();
      }
      this.getSettlements();
    }).catch(err => {
      console.log("Something went wrong");
      console.log(err);
      alert("Something went wrong, try again")
      console.log(err)
    });
    this.paymentForm.reset({ selectedCard: '', cvv: '' });
  }


  onRemind(receiver_user_email: string) {

    this.httpClientService.sendReminder(receiver_user_email).then(data => {
      alert(data);
    }).catch(err => {
      console.log("Something went wrong");
      console.log(err);
      alert("Something went wrong, try again")
    });
  }
}


















