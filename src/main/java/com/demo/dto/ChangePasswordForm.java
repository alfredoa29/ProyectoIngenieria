package com.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangePasswordForm {

    @NotNull
    private long id;

    @NotBlank(message = "Current password must be no blank")
    private String currentPassword;

    @NotBlank(message = "New password must be no blank")
    private String newPassword;

    @NotBlank(message = "Confirm password must be no blank")
    private String confirmPassword;

    public ChangePasswordForm() {
    }

    public ChangePasswordForm(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
