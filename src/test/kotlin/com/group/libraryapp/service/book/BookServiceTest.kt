package com.group.libraryapp.service.book

import com.group.libraryapp.domain.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookService: BookService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
){
    @AfterEach
    fun clean() {
        bookRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("책 등록 기능")
    fun `saveBook`() {
        //given
        val request = BookRequest("어린왕자")

        //when
        bookService.saveBook(request)

        //then
        val books = bookRepository.findAll()
        assertThat(books).hasSize(1)
        assertThat(books[0].name).isEqualTo("어린왕자")
    }

    @Test
    @DisplayName("책 대출 정상")
    fun `loanBookTest`() {
        //given
        bookRepository.save(Book("어린왕자"))
        val savedUser = userRepository.save(User("injin", null))
        val request = BookLoanRequest("injin", "어린왕자")

        //when
        bookService.loanBook(request)

        //then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].bookName).isEqualTo("어린왕자")
        assertThat(results[0].user.id).isEqualTo(savedUser.id)
        assertThat(results[0].isReturn).isFalse
    }

    @Test
    @DisplayName("책이 대출상태라면, 신규 대츌이 실패한다.")
    fun `loanBookFailTest`() {
        //given
        bookRepository.save(Book("어린왕자"))
        val savedUser = userRepository.save(User("injin", null))
        userLoanHistoryRepository.save(UserLoanHistory(savedUser, "어린왕자", false))
        val request = BookLoanRequest("injin", "어린왕자")

        //when && then
        val message = assertThrows<java.lang.IllegalArgumentException> {
            bookService.loanBook(request)
        }.message
        assertThat(message).isEqualTo("진작 대출되어 있는 책입니다")
    }

    @Test
    @DisplayName("책 반납이 정상 동작")
    fun `returnBookTest`() {
        //given
        val savedUser = userRepository.save(User("injin", null))
        userLoanHistoryRepository.save(UserLoanHistory(savedUser, "어린왕자", false))
        val request = BookReturnRequest("injin", "어린왕자")

        //when
        bookService.returnBook(request)

        //then
        val results = userLoanHistoryRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].isReturn).isTrue()
    }
}