package com.lcaohoanq.samplewebapplication.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private String address;

    @JsonProperty("password")
    @JsonIgnore
    private String password;

    @JsonProperty("is_active")
    private int isActive;

    @JsonProperty("is_subscription")
    private int isSubscription;

    @JsonProperty("status_name")
    private String statusName;

    @JsonProperty("date_of_birth")
    private String dob;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("google_account_id")
    private int googleAccountId;

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("account_balance")
    private long accountBalance;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

}
