package com.group.libraryapp.controller.book;

import com.group.libraryapp.dto.book.request.JavaBookLoanRequest;
import com.group.libraryapp.dto.book.request.JavaBookRequest;
import com.group.libraryapp.dto.book.request.JavaBookReturnRequest;
import com.group.libraryapp.service.book.JavaBookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/java")
public class JavaBookController {

  private final JavaBookService bookService;

  public JavaBookController(JavaBookService bookService) {
    this.bookService = bookService;
  }

  @PostMapping("/book")
  public void saveBook(@RequestBody JavaBookRequest request) {
    bookService.saveBook(request);
  }

  @PostMapping("/book/loan")
  public void loanBook(@RequestBody JavaBookLoanRequest request) {
    bookService.loanBook(request);
  }

  @PutMapping("/book/return")
  public void returnBook(@RequestBody JavaBookReturnRequest request) {
    bookService.returnBook(request);
  }

}
