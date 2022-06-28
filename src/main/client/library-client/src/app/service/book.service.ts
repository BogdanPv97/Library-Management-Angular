import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';

import { tap, map, catchError } from 'rxjs/operators';

import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Book } from '../model/book';

@Injectable({
  providedIn: 'root',
})
export class BookService {
  constructor(private http: HttpClient) {
    this.getAllBooks().subscribe();
  }

  booksChanged = new BehaviorSubject<Book[]>(null);

  private handleError(error: HttpErrorResponse): Observable<any> {
    let errorMessage: string;

    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error:${error.error.message}`;
    } else {
      if (error.error.reason) {
        errorMessage = error.error.reason.message;
      } else {
        errorMessage = error.status.toString();
      }
    }

    return throwError(errorMessage);
  }

  public getAllBooks(): Observable<any> {
    return this.http.get<any>('http://localhost:9292/api/books').pipe(
      tap((response) => {
        this.booksChanged.next(response);
      }),
      catchError(this.handleError)
    );
  }

  public saveBook(book: Book): Observable<Book> {
    this.booksChanged.next([...this.booksChanged.value, book]);
    return this.http.post<Book>('http://localhost:9292/api/save', book);
  }
}
