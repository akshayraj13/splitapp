import { Component, OnInit } from '@angular/core';
import { HttpClientService } from '../service/http-client.service';
import { Card } from '../models/card';

@Component({
  selector: 'app-viewcard',
  templateUrl: './viewcard.component.html',
  styleUrls: ['./viewcard.component.css']
})

export class ViewcardComponent implements OnInit {

  card: Card;

  constructor(
    private httpClientService: HttpClientService
  ) { }

  ngOnInit() {
    this.httpClientService.getCardByEmail().subscribe(card => {
      this.card = card;
    });
  }

}


