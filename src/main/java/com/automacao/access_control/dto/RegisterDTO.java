package com.automacao.access_control.dto;

import com.automacao.access_control.enuns.UserPermissions;

public record RegisterDTO(String name, String email, String password, UserPermissions permission) {

}
