package com.c702t.Cerveza.models.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Class representing an Business Organization Request.")
public class BusinessRequest {

    @NonNull
    @NotEmpty(message = "the name can't be null")
    @NotBlank(message = "the name can't  be blank")
    @ApiModelProperty(notes = "Name of the Business",
            example = "PATAGONIA", required = true)
    private String name;

    @NonNull
    @NotEmpty(message = "the image can't be null")
    @NotBlank(message = "the image can't  be blank")
    @ApiModelProperty(notes = "Profile Image of the Business",
            example = "imagen.jpg",
            required = true)
    private String image;

    @NonNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    @ApiModelProperty(notes = "Address of the Business",
            example = "Av. San Martín 550",
            required = true)
    private String address;

    @NonNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    @ApiModelProperty(notes = "City of the Business",
            example = "Cordoba",
            required = true)
    private String city;

    @NonNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    @ApiModelProperty(notes = "State of the Business",
            example = "Cordoba",
            required = true)
    private String state;

    @NonNull
    @NotEmpty(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    @ApiModelProperty(notes = "Country of the Business",
            example = "Argentina",
            required = true)
    private String country;

    @ApiModelProperty(notes = "Phone of the Business",
            example = "235671889",
            required = true)
    private Integer phone;


    @ApiModelProperty(notes = "email of the Business",
            example = "patagonia@mail.com",
            required = true)
    private String email;

    @ApiModelProperty(notes = "description of the Business",
            example = "Cerveceria especializada en cerveza",
            required = true)
    private String aboutUsText;

    @ApiModelProperty(notes = "Facebook of the organization",
            example = "patagonia.facebook.com",
            required = true)
    private String urlFacebook;

    @ApiModelProperty(notes = "Instagram of the organization",
            example = "patagonia.instagram.com",
            required = true)
    private String urlInstagram;


    @ApiModelProperty(notes = "id of the user corresponding to the Business", example = " ")
    private Long idUser;




}
