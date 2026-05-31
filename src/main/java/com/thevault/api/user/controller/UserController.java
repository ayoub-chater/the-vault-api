package com.thevault.api.user.controller;

import com.thevault.api.user.dto.AvatarDto;
import com.thevault.api.user.dto.UpdateAvatarRequest;
import com.thevault.api.user.dto.UpdateProfileRequest;
import com.thevault.api.user.dto.UserDto;
import com.thevault.api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "User Profile", description = "Profile management and avatar configuration")
@RestController
@RequestMapping("/api/users/me")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get current user profile")
    @GetMapping
    public ResponseEntity<UserDto> getProfile() {
        return ResponseEntity.ok(userService.getCurrentUserProfile());
    }

    @Operation(summary = "Update profile information")
    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateProfile(@Valid @RequestBody UpdateProfileRequest request) {
        return ResponseEntity.ok(userService.updateProfile(request));
    }

    @Operation(summary = "Upload profile picture")
    @PutMapping(value = "/profile-picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDto> updateProfilePicture(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(userService.updateProfilePicture(file));
    }

    @Operation(summary = "Delete profile picture")
    @DeleteMapping("/profile-picture")
    public ResponseEntity<Void> deleteProfilePicture() {
        userService.deleteProfilePicture();
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get avatar configuration")
    @GetMapping("/avatar")
    public ResponseEntity<AvatarDto> getAvatar() {
        AvatarDto avatar = userService.getAvatar();
        return avatar != null ? ResponseEntity.ok(avatar) : ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create or update avatar configuration")
    @PutMapping("/avatar")
    public ResponseEntity<AvatarDto> updateAvatar(@RequestBody UpdateAvatarRequest request) {
        return ResponseEntity.ok(userService.updateAvatar(request));
    }

    @Operation(summary = "Delete avatar")
    @DeleteMapping("/avatar")
    public ResponseEntity<Void> deleteAvatar() {
        userService.deleteAvatar();
        return ResponseEntity.noContent().build();
    }
}
