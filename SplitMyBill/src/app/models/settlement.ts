export class Settlement {
    constructor(
      public settlementmsg: string,
      public billtopay: number,
      public friend_email: string,
      public status: number
    ) { }
  }
  