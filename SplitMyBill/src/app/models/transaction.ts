export class Transaction {
  constructor(
    public user_email: string,
    public friend_email: string,
    public billtopay: number,
    public dateoftransaction: Date
  ) { }
}
