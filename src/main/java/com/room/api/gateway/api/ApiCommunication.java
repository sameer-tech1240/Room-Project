package com.room.api.gateway.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.room.api.gateway.dto.BookDTO;
import com.room.api.gateway.dto.JsonHolderResponseDTO;
import com.room.api.gateway.service.ApiCommunicationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ApiCommunication {

    @Autowired
    private ApiCommunicationService apiCommunicationService;

    @GetMapping("/apiCommunication/{id}")
    public BookDTO apiCall(@PathVariable int id) {
        BookDTO bookDTO = apiCommunicationService.apiCall(id);
        log.info("api response :{}", bookDTO);
        return bookDTO;
    }


    @GetMapping("/apiCallJsonHolder/{id}")
    public JsonHolderResponseDTO apiCallToJsonHolder(@PathVariable int id) throws JsonProcessingException {
        JsonHolderResponseDTO jsonHolderResponseDTO = apiCommunicationService.apiCallToJsonHolder(id);
        if (jsonHolderResponseDTO != null) {
            log.info("api response :{}", jsonHolderResponseDTO);
            return jsonHolderResponseDTO;
        }
        return null;
    }
}

