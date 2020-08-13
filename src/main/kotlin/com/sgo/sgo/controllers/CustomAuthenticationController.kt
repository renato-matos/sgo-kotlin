package com.sgo.sgo.controllers

import com.sgo.sgo.auth.AuthenticationRequest
import com.sgo.sgo.auth.AuthenticationResponse
import com.sgo.sgo.services.CustomAuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authentication")
class CustomAuthenticationController {

    @Autowired
    lateinit var customAuthenticationService: CustomAuthenticationService

    @PostMapping
    fun authenticateUser(@RequestBody authenticationRequest: AuthenticationRequest) :
            ResponseEntity<AuthenticationResponse> {

        val auth = customAuthenticationService.authenticateUser(authenticationRequest.username,
                                                                authenticationRequest.password)
        val authResponse: AuthenticationResponse = AuthenticationResponse(auth.accessToken, auth.expiresIn,
                                                                auth.refreshToken, auth.refreshExpiresIn)
        return ResponseEntity.ok().body(authResponse)
    }

}