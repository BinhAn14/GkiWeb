package com.example.gkiweb.controller;

import com.example.gkiweb.model.Book;
import com.example.gkiweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController() {
    }

    // API GET danh sách sách
    @GetMapping("/books")
    @ResponseBody
    public List<Book> getBookList() {
        return bookService.findAll();
    }

    // API GET sách theo ID
    @GetMapping("/books/{id}")
    @ResponseBody
    public ResponseEntity<Book> getBookById(@PathVariable("id") int bookId) {
        for (Book book : bookService.findAll()) {
            if (book.getId() ==bookId) {
                return ResponseEntity.status(200).body(book);
            }
        }
        return ResponseEntity.status(404).body(null);
    }

    // API DELETE sách theo ID
    @DeleteMapping("/books/{id}")
    @ResponseBody
    public List<Book> removeBookById(@PathVariable("id") Long bookId) {
        bookService.delete(bookId);
        return bookService.findAll();
    }

    // API POST tạo sách mới
    @PostMapping("/books")
    @ResponseBody
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        bookService.save(book);
        return ResponseEntity.status(201).body(book);
    }

    // API PUT cập nhật sách theo ID
    @PutMapping("/books/{id}")
    @ResponseBody
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long bookId, @RequestBody Book updateBook) {
        Book book = bookService.findById(bookId);
        if (book != null) {
            book.setTitle(updateBook.getTitle());
            book.setAuthor(updateBook.getAuthor());
            book.setNhaXB(updateBook.getNhaXB());
            book.setNamXB(updateBook.getNamXB()); 
            book.setPrice(updateBook.getPrice()); 
            bookService.save(book);
            return ResponseEntity.status(200).body(book);
        }
        return ResponseEntity.status(404).body(null);
    }
}
