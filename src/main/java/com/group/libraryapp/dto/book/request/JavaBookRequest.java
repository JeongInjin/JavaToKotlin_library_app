package com.group.libraryapp.dto.book.request;

public class JavaBookRequest {

  public JavaBookRequest(String name) {
    this.name = name;
  }

  private String name;

  public String getName() {
    return name;
  }

}
