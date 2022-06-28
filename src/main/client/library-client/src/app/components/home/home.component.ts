import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, startWith, tap } from 'rxjs/operators';
import { AppState } from 'src/app/model/appstate';
import { Book } from 'src/app/model/book';
import { BookService } from 'src/app/service/book.service';
import { DataState } from '../enum/DataState';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  public DataState = DataState;

  public appState$: Observable<AppState<Book[]>>;

  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    this.appState$ = this.bookService.booksChanged.pipe(
      tap(console.log),
      map((response) => {
        return {
          dataState: DataState.LOADED,
          data: response,
        };
      }),
      startWith({ dataState: DataState.LOADING }),
      catchError((error: string) =>
        of({ dataState: DataState.Error, error: error })
      )
    );

    this.appState$.subscribe(console.log);
  }
}
