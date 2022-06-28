package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.BookDTO;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() throws InterruptedException {

//        TimeUnit.SECONDS.sleep(3);
        List<Book> books = bookService.getAllBooks();



        if(books!=null){
            return new ResponseEntity<>(books, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@RequestBody BookDTO book){
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBookById(@PathVariable(name="bookId") Long bookId){
        try{
            bookService.deleteBook(bookId);
            return new ResponseEntity<String>("Book succesfully removed", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>("Book not found", HttpStatus.NOT_FOUND);
    }
}
