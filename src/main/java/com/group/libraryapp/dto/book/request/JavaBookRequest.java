package com.group.libraryapp.dto.book.request;

import com.group.libraryapp.domain.book.BookType;

public class JavaBookRequest {

  public JavaBookRequest(String name, BookType bookType) {
    this.name = name;
    this.bookType = bookType;
  }

  public JavaBookRequest(String name) {
    this.name = name;
  }

  private String name;

  private BookType bookType;

  public String getName() {
    return name;
  }

  public BookType getBookType() {
    return bookType;
  }
}
