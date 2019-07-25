export class Card {
  constructor(
    public email: string,
    public month: string,
    public year: number,
    public cvv: number,
    public cardnumber: number,
    public cardid: number,
    public cardholdername: string,
    public cardname: string,
    public accbal: number
  ) { }
}