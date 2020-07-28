package com.sgo.sgo.exceptions

import java.lang.RuntimeException

class ExistingPersonException(private val document: Long) : RuntimeException("Person with document " + document + " already exists")