package com.sgo.sgo.config

import com.sgo.sgo.data.ClientRepository
import com.sgo.sgo.data.PersonRepository
import com.sgo.sgo.entities.Client
import com.sgo.sgo.entities.Person
import com.sgo.sgo.entities.enums.EntityType
import com.sgo.sgo.entities.enums.PersonType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class TestConfig : CommandLineRunner {

    @Autowired
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var personRepository: PersonRepository

    override fun run(vararg args: String?) {
        val person1 = Person(1, EntityType.CLIENT, PersonType.INDIVIDUAL)
        val person2 = Person(2, EntityType.CLIENT, PersonType.INDIVIDUAL)
        personRepository.saveAll(listOf(person1, person2))

        val cliente1 = Client(1L,"Renato",28867321846, null)
        cliente1.person = person1
        val cliente2 = Client(2L,"Fabi",28867321846, null)
        cliente2.person = person2
        clientRepository.saveAll(listOf(cliente1, cliente2))
    }

}