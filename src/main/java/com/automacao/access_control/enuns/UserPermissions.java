package com.automacao.access_control.enuns;

public enum UserPermissions {
	
	ADMIN("admin"),
	USER("user");
	
	private String permission;
	
	UserPermissions(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return this.permission;
	}
}
