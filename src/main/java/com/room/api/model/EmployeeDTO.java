package com.room.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.room.api.common.RoomApiCommonDTO;
import lombok.*;

@JsonInclude(value = Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
@Data
public class EmployeeDTO extends RoomApiCommonDTO {

	private int id;
	private String name;
	private String address;
	private int zipCode;
	private String city;
}
