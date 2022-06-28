export class Book {
  constructor(
    public title: string,
    public author: string,
    public genre: string,
    public year: number,
    public id?: number
  ) {}
}
