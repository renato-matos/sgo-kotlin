package com.sgo.sgo.auth

import javax.validation.constraints.NotEmpty

class AuthenticationRequest (@field:NotEmpty
                             val username: String,
                             @field:NotEmpty
                             val password: String) {
}