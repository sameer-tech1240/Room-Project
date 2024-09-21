package com.room.api.mask;

import com.room.api.mask.dto.UserAccountDetailsDTO;
import com.room.api.mask.service.IUserMaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/user-info")
@Log4j2
public class MaskApi {

    @Autowired
    private IUserMaskService userMaskService;

    @RequestMapping(value = "/userDataMask", method = RequestMethod.POST)
    public UserAccountDetailsDTO createUser(@RequestBody UserAccountDetailsDTO userAccountDetailsDTO) {
        UserAccountDetailsDTO accountDetailsDTOResponse = userMaskService.createUser(userAccountDetailsDTO);
        if (accountDetailsDTOResponse != null) {
            return accountDetailsDTOResponse;
        }
        return accountDetailsDTOResponse;
    }
}
