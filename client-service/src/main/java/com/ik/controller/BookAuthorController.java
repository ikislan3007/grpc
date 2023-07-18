package com.ik.controller;

import com.ik.service.BookAuthorClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BookAuthorController {
    BookAuthorClientService bookAuthorClientService;
}
