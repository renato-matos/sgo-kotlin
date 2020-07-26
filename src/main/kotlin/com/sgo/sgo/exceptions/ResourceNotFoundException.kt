package com.sgo.sgo.exceptions

import java.lang.RuntimeException

class ResourceNotFoundException(private val id: Long) : RuntimeException("Resource not found. Id:" + id) {

}