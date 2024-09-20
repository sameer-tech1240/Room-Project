package com.room.api.gateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.api.gateway.dto.BookDTO;
import com.room.api.gateway.entity.BookEntity;
import com.room.api.gateway.repo.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Log4j2
public class ApiCommunicationService {

    @Autowired
    private ModelMapper  modelMapper;

    @Autowired
    private BookRepository bookRepository;

    public BookDTO apiCall(int id) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String url = "http://192.168.1.106:8088/api/get/" + id;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(50000);
            requestFactory.setReadTimeout(50000);

            httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            RequestEntity<?> requestEntity = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create(url));
            ResponseEntity<String> apiResponse = restTemplate.exchange(requestEntity, String.class);
            if (apiResponse.hasBody()) {
                String body = apiResponse.getBody();
                BookDTO bookDTO = objectMapper.readValue(body, BookDTO.class);
                BookEntity bookEntity = modelMapper.map(bookDTO, BookEntity.class);
                bookRepository.save(bookEntity);
                BookDTO bookDTO1 = objectMapper.readValue(body, BookDTO.class);
                log.info("api response details:{}", bookEntity);
                return bookDTO1;
            }

        } catch (Exception e) {
            log.error("exception occurred while comminicating with api{}",e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
