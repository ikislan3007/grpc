package com.ik;

import io.grpc.stub.StreamObserver;
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
    @Override
    public void getAuthor(Author request, StreamObserver<Author> responseObserver) {
        TempDb.getAuthorsFromTempDb().stream()
                .filter(author -> author.getAuthorId() == request.getAuthorId())
                .findFirst()
                .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
