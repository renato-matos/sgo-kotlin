package com.sgo.sgo.services

import com.sgo.sgo.data.PersonRepository
import com.sgo.sgo.entities.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonService {

    @Autowired
    lateinit var personRepository: PersonRepository

    fun insert(person: Person) : Person {
        return personRepository.save(person)
    }
}