
export class Expense {
  constructor(
    public user_email: string,
    public friend_email: string[],
    public expense: number

  ) { }
}
