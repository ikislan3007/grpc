package com.ik.service;

import com.google.protobuf.Descriptors;
import com.ik.Author;
import com.ik.AuthorIdRequest;
import com.ik.BookAuthorServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BookAuthorClientService {

    @GrpcClient("grpc-example-service")
    BookAuthorServiceGrpc.BookAuthorServiceBlockingStub synchronousClient;

    public Map<Descriptors.FieldDescriptor, Object> getAuthor(int authorId) {
        Author authorRequest = Author.newBuilder().setAuthorId(authorId).build();
        Author authorResponse = synchronousClient.getAuthor(authorRequest);
        return authorResponse.getAllFields();
    }

    public Map<Descriptors.FieldDescriptor, Object> getAuthorById(int id) {
        AuthorIdRequest authorRequest = AuthorIdRequest.newBuilder().setId(id).build();
        Author authorResponse = synchronousClient.getAuthorById(authorRequest);
        return authorResponse.getAllFields();
    }


}
