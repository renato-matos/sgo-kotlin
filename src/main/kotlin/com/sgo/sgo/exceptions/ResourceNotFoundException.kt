package com.sgo.sgo.exceptions

import java.lang.RuntimeException

class ResourceNotFoundException(private val resource: String, private val id: Long) :
        RuntimeException("$resource not found. Id:$id") {

}