package com.automacao.access_control.dto;

import com.automacao.access_control.enuns.UserPermissions;

public record RegisterDTO(String name, String email, String password, UserPermissions permission, String rfid) {

    public RegisterDTO(String name, String email, String password, UserPermissions permission) {
        this(name, email, password, permission, null);
    }

    public RegisterDTO withRfid(String rfid) {
        return new RegisterDTO(this.name, this.email, this.password, this.permission, rfid);
    }
}
