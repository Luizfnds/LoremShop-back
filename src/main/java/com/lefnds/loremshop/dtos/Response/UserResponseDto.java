package com.lefnds.loremshop.dtos.Response;

import com.lefnds.loremshop.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String name;
    private String surname;
    private String email;

    public static UserResponseDto userToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }
}
