package com.ik;

import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BookAuthorService  extends BookAuthorServiceGrpc.BookAuthorServiceImplBase{

    /**
     * <pre>
     * unary - synchronous
     * client will send one request and server will respond with one response.
     * </pre>
     *
     * @param request
     * @param responseObserver
     */

    TempDb tempDb;

    @Override
    public void getAuthor(Author request, StreamObserver<Author> responseObserver) {
        tempDb.getAuthorsFromTempDb().stream()
                .filter(author -> author.getAuthorId() == request.getAuthorId())
                .findFirst()
                .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }


    @Override
    public void getAuthorById(AuthorIdRequest request, StreamObserver<Author> responseObserver) {
        TempDb.getAuthorsFromTempDb().stream()
            .filter(author -> author.getAuthorId() == request.getId())
            .findFirst()
            .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }


    @Override
    public void getBooksByAuthor(Author request, StreamObserver<Book> responseObserver) {

        tempDb.getBooksFromTempDb()
            .stream()
            .filter(book -> book.getAuthorId() == request.getAuthorId())
            .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Book> getExpensiveBook(StreamObserver<Book> responseObserver) {
        return new StreamObserver<Book>() {
            Book expensiveBook = null;
            float priceTrack = 0;

            @Override
            public void onNext(Book book) {
                if (book.getPrice() > priceTrack) {
                    priceTrack = book.getPrice();
                    expensiveBook = book;
                }
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(expensiveBook);
                responseObserver.onCompleted();
            }
        };
    }


    @Override
    public StreamObserver<Book> getBooksByGender(StreamObserver<Book> responseObserver) {


        return new StreamObserver<Book>() {
            List<Book> bookList = new ArrayList<>();

            @Override
            public void onNext(Book book) {
                tempDb.getBooksFromTempDb()
                    .stream()
                    .filter(bookFromDb -> bookFromDb.getAuthorId() == book.getAuthorId())
                    .forEach(bookList::add);
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                bookList.forEach(responseObserver::onNext);
                responseObserver.onCompleted();
            }
        };
    }
}

