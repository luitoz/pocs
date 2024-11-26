package org.acme;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {

    @NotNull(message = "{user.name.notblank}")
    private String name;


    @Size(min = 10, max = 200, message 
      = "{user.email.size}")
    private String email;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


}
