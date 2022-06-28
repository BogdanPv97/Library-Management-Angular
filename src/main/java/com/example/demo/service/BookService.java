package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.BookDTO;
import com.example.demo.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        try{
            return bookRepository.findAll();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Book saveBook(BookDTO bookDTO){
        Book book = bookDtoToEntitySave(bookDTO);
        try{
          return bookRepository.save(book);
        }catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteBook(Long bookId){
        try{
            bookRepository.deleteById(bookId);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Book bookDtoToEntitySave(BookDTO bookDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        Book book = new Book();
        book = modelMapper.map(bookDTO, Book.class);

        return book;
    }
}
