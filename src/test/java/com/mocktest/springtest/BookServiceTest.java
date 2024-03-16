package com.mocktest.springtest;

import com.mocktest.springtest.model.Book;
import com.mocktest.springtest.repository.BookRepository;
import com.mocktest.springtest.service.BookService;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.http.HttpResponse;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
// mvn test -Dgroups=unit using such a command we can individually test different test TAG groups

@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;


    @BeforeAll
    public static void beforeAll(){
        System.out.println("-------------------Starting Tests for dev env----------------");
    }
    @AfterAll
    public static void AfterAll(){
        System.out.println("-------------------Ending Tests for dev env----------------");
    }
    @Test
    @Tag("unit")
    @DisplayName("get books test")
    public void getbookstest(){
        when(bookRepository.findAll()).thenReturn(Stream.of(new Book(451,"bookname1","bookauthor1"),
                new Book(452,"bookname2","bookauthor2")).collect(Collectors.toList()));
        assertEquals(2,bookService.getAllBooks().size());
    }

    @Test
    @Tag("unit")
    @DisplayName("save empty book test")
    public void testSaveBookWithNullBook() {

        assertThrows(NullPointerException.class, () -> {
            bookService.saveBook(null);
        });
    }
    @Test
    @Tag("non-unit")
    @DisplayName("save empty book test v2 non unit")
    @Disabled
    public void testSaveBookWithNullBookv2() {

        assertThrows(NullPointerException.class, () -> {
            bookService.saveBook(null);
        });
    }

    @Test
    @Tag("unit")
    @DisplayName("get books test but timeout test")
     public void getbookstesttimeouttest() throws InterruptedException {
        when(bookRepository.findAll()).thenReturn(Stream.of(new Book(451,"bookname1","bookauthor1"),
                new Book(452,"bookname2","bookauthor2")).collect(Collectors.toList()));
        assertEquals(2,bookService.getAllBooks().size());
    }

    @Nested
    class FindBooksbyID{
        int id;
        Random random = new Random();
        @BeforeEach
        void setup(){

            id= random.nextInt(0,15000);
            Book book =new Book(id,"newBook","deadbeat author");
            when(bookRepository.findById(id)).thenReturn(java.util.Optional.of(book));
        }


        @Test
        public void bookWithIdShouldExist(){
            Book book = bookService.getBookById(id);
            assertEquals(book.getId(),id);
        }

        @Test
        public void bookWithIdShouldNotExist(){
            Book book = bookService.getBookById(random.nextInt(0,15000));
            assertNull(book);
        }


    }
}
