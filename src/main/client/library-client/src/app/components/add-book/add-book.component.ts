import { IfStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Book } from 'src/app/model/book';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css'],
})
export class AddBookComponent implements OnInit {
  createBookFromGroup: FormGroup;

  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    this.createBookFromGroup = new FormGroup({
      title: new FormControl('', Validators.required),
      author: new FormControl('', Validators.required),
      genre: new FormControl('', Validators.required),
      year: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[1-9]+[0-9]*$/),
      ]),
    });
  }

  validFormCheck() {
    let valid: boolean = true;
    for (let e in this.createBookFromGroup.value) {
      if (this.createBookFromGroup.value[e] == '') {
        alert(e + ' is required');
        valid = false;
      }
    }
  }

  saveBook() {
    if (this.createBookFromGroup.valid == true) {
      let book = new Book(
        this.createBookFromGroup.get('title').value,
        this.createBookFromGroup.get('author').value,
        this.createBookFromGroup.get('genre').value,
        this.createBookFromGroup.get('year').value
      );

      console.log(book);

      this.bookService.saveBook(book).subscribe();
    } else {
      this.validFormCheck();
    }
  }
}
