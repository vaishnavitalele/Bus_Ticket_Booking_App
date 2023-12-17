package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdminLoginDTO {
	
	@Size(min=6, message="Length Should be 6 or Above")
	@Pattern(regexp ="^Admin(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{7,}$", message = "Type Correct pattern")
	private String adminname;
	
	@NotBlank(message = "Name Should not be Blank")
	@NotNull(message ="Password Should not be NUll")
	@Pattern(regexp ="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$",
	message = " InCorrect pattern")
	private String password;
	
	public AdminLoginDTO() {}

	public AdminLoginDTO(
			@Size(min = 6, message = "Length Should be 6 or Above") @Pattern(regexp = "^Admin(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{7,}$", message = "Type Correct pattern") String adminname,
			@NotBlank(message = "Name Should not be Blank") @NotNull(message = "Password Should not be NUll") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$", message = " InCorrect pattern") String password) {
		super();
		this.adminname = adminname;
		this.password = password;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
