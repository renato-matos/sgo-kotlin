package com.sgo.sgo.auth

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class KeycloakAuthenticationResponse
        (@JsonProperty("access_token")
        val accessToken: String,
        @JsonProperty("expires_in")
        val expiresIn: Long,
        @JsonProperty("refresh_expires_in")
        val refreshExpiresIn: Long,
        @JsonProperty("refresh_token")
        val refreshToken: String,
        @JsonProperty("token_type")
        val tokenType: String,
        @JsonProperty("not-before-policy")
        val notBeforePolicy: Long,
        @JsonProperty("session_state")
        val sessionState: String,
        @JsonProperty("scope")
        val scope: String)
{


}