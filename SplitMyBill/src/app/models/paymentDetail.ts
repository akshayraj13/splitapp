export class PaymentDetail {
  constructor(
    public cardnumber: number,
    public cvv: number,
    public friend_email: string,
    public billtopay: number,
    public user_email: string
  ) { }
}
