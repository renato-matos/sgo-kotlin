package com.sgo.sgo.services

import com.sgo.sgo.auth.KeycloakAuthenticationResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject

@Service
class CustomAuthenticationService {

    fun authenticateUser(username: String, password: String) : KeycloakAuthenticationResponse {
        val request = RestTemplate()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val arguments = LinkedMultiValueMap<String, String>()
        arguments.add("grant_type","password")
        arguments.add("client_id","sgo-kotlin")
        arguments.add("client_secret","43d18a4c-ae80-420d-8355-2429f1829a23")
        arguments.add("username",username)
        arguments.add("password", password)

        val req = HttpEntity<MultiValueMap<String, String>>(arguments, headers)

        val url = "http://localhost:8080/auth/realms/sgo/protocol/openid-connect/token"
        return request.postForObject(url,req,KeycloakAuthenticationResponse::class,arguments)
    }
}