package com.sgo.sgo.exceptions

import java.lang.RuntimeException

class ExistingPersonException(document: Long) :
        RuntimeException("Person with document $document already exists")