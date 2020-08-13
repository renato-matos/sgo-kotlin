package com.sgo.sgo.exceptions

import java.lang.RuntimeException

class UnauthorizedException(username: String) :
        RuntimeException("Could not authorize user $username") {
}