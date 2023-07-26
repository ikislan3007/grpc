package com.ik;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args){
            SpringApplication.run(ClientApplication.class, args);


    ManagedChannel channel = ManagedChannelBuilder
        .forAddress("localhost", 8090)
        .usePlaintext()
        .build();

    BookAuthorServiceGrpc.BookAuthorServiceBlockingStub stub =
        BookAuthorServiceGrpc.newBlockingStub(channel);


    Author request =  Author.newBuilder().setAuthorId(1).build();
    Author response = stub.getAuthor(request);

        AuthorIdRequest authorIdRequest = AuthorIdRequest.newBuilder().setId(2).build();
        Author resp = stub.getAuthorById(authorIdRequest);
        System.out.println(response);

        System.out.println(resp);

        }
}
