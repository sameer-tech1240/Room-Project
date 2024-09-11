package com.room.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.room.api.common.RoomApiCommonDTO;

@JsonInclude(value = Include.NON_NULL)
public class EmployeeDTO extends RoomApiCommonDTO {

	private int id;
	private String name;
	private String address;
	private int zipCode;
	private String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", zipCode=" + zipCode + ", city="
				+ city + "]";
	}

	public EmployeeDTO(int id, String name, String address, int zipCode, String city) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
	}

	public EmployeeDTO() {
		super();

	}
}
