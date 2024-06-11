package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.entity.Book;
import net.enjoy.springboot.registrationlogin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// BookController.java

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title) {
        return bookService.searchBooksByTitle(title);
    }

    @PostMapping("/borrow/{id}")
    public void borrowBook(@PathVariable Long id, @RequestBody String note) {
        if (!bookService.borrowBook(id, note)) {
            throw new RuntimeException("Borrowing book failed.");
        }
    }

    @PostMapping("/return/{id}")
    public void returnBook(@PathVariable Long id) {
        if (!bookService.returnBook(id)) {
            throw new RuntimeException("Returning book failed.");
        }
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("/{id}") // 添加删除书本的映射
    public void deleteBook(@PathVariable Long id) {
        if (!bookService.deleteBook(id)) {
            throw new RuntimeException("Deleting book failed.");
        }
    }
}

