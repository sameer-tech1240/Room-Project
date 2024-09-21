package com.room.api.gateway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.room.api.gateway.dto.BookDTO;
import com.room.api.gateway.dto.JsonHolderResponseDTO;
import com.room.api.gateway.entity.BookEntity;
import com.room.api.gateway.repo.BookRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Log4j2
public class ApiCommunicationService {


    @Value("${json.holder.api.response}")
    String urlToCallJsonHolder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private BookRepository bookRepository;

    public BookDTO apiCall(int id) {
        try {

            String url = "http://192.168.1.106:8088/api/get/" + id;

            HttpHeaders httpHeaders = new HttpHeaders();
            this.apiCallTimeout();
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
            log.error("exception occurred while comminicating with api{}", e);
            throw new RuntimeException(e);
        }
        return null;
    }

    private void apiCallTimeout() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);
    }


    public JsonHolderResponseDTO apiCallToJsonHolder(int id) throws JsonProcessingException {

        String url = this.buildUrl(id);
        String forObject = restTemplate.getForObject(url, String.class);
        if (!forObject.isEmpty()) {
            //conversion

            JsonHolderResponseDTO jsonHolderResponseDTO = objectMapper.readValue(forObject, JsonHolderResponseDTO.class);
            return jsonHolderResponseDTO;
        }
        log.info("api response holder:{}", forObject);


        return null;
    }

    private String buildUrl(int id) {
        return urlToCallJsonHolder + id;
    }
}
