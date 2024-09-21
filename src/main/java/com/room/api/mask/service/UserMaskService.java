package com.room.api.mask.service;

import com.room.api.mask.dto.UserAccountDetailsDTO;
import com.room.api.mask.entity.UserAccountDetailsEntity;
import com.room.api.mask.repo.IUserMaskRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserMaskService implements IUserMaskService {
    @Autowired
    private IUserMaskRepository userMaskRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserAccountDetailsDTO createUser(UserAccountDetailsDTO userAccountDetailsDTO) {
        log.info("method start with  userAccountDetailsDTO :{}", userAccountDetailsDTO);
        if (userAccountDetailsDTO != null) {
            UserAccountDetailsEntity userAccountDetails = modelMapper.map(userAccountDetailsDTO, UserAccountDetailsEntity.class);

            UserAccountDetailsEntity userAccountDetailsEntity = userMaskRepository.save(userAccountDetails);
            log.info("user details persist :{}", userAccountDetailsEntity);
            UserAccountDetailsDTO userAccountDetailResponse = modelMapper.map(userAccountDetailsEntity, UserAccountDetailsDTO.class);
            return userAccountDetailResponse;
        }
        return null;
    }
}
