package com.sgo.sgo.auth

import com.fasterxml.jackson.annotation.JsonProperty

class AuthenticationResponse (@JsonProperty("access_token")
                              val accessToken: String,
                              @JsonProperty("expires_in")
                              val expiresIn: Long,
                              @JsonProperty("refresh_token")
                              val refreshToken: String,
                              @JsonProperty("refresh_token_expires_in")
                              val refreshTokenExpiresIn: Long) {
}