package com.sgo.sgo.services

import com.sgo.sgo.data.PersonRepository
import com.sgo.sgo.entities.Person
import com.sgo.sgo.exceptions.ExistingPersonException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonService {

    @Autowired
    lateinit var personRepository: PersonRepository

    fun insert(person: Person) : Person {
        if (findByDocument(person.document) == null) {
            return personRepository.save(person)
        } else {
            throw ExistingPersonException(person.document)
        }
    }

    fun findByDocument(document: Long) : Person? {
        return personRepository.findByDocument(document)
    }
}