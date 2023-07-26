package com.ik.controller;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.ik.service.BookAuthorClientService;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BookAuthorController {

    final BookAuthorClientService bookAuthorClientService;

    @GetMapping("/author/{authorId}")
    public Map<FieldDescriptor, Object> getAuthor(@PathVariable String authorId) {
        return bookAuthorClientService.getAuthor(Integer.parseInt(authorId));
    }

    @GetMapping("/author/{id}")
    public Map<FieldDescriptor, Object> getAuthorById(@PathVariable Integer id) {
        return bookAuthorClientService.getAuthorById(id);
    }
}
