package com.sgo.sgo.config

import com.sgo.sgo.data.AddressRepository
import com.sgo.sgo.data.ClientRepository
import com.sgo.sgo.data.PersonRepository
import com.sgo.sgo.data.SupplierRepository
import com.sgo.sgo.entities.Address
import com.sgo.sgo.entities.Client
import com.sgo.sgo.entities.Person
import com.sgo.sgo.entities.Supplier
import com.sgo.sgo.entities.enums.AddressType
import com.sgo.sgo.entities.enums.EntityType
import com.sgo.sgo.entities.enums.PersonType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import java.time.Instant
import java.util.*

@Configuration
class TestConfig : CommandLineRunner {

    @Autowired
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var supplierRepository: SupplierRepository

    @Autowired
    lateinit var addressRepository: AddressRepository

    @Autowired
    lateinit var personRepository: PersonRepository

    override fun run(vararg args: String?) {
        val person1 = Person(0,EntityType.CLIENT, PersonType.INDIVIDUAL,"Cliente numero 1", null, 288867321846, Instant.now(), Instant.now())
        val person2 = Person(0,EntityType.SUPPLIER, PersonType.LEGAL,"Fornecedor numero 1", null, 4753496000125, Instant.now(), Instant.now())
        personRepository.saveAll(listOf(person1, person2))

        val cliente1 = Client(0)
        cliente1.person = person1
        clientRepository.save(cliente1)

        val fornecedor1 = Supplier(0,"Construcao","contato teste", null,null,
                "teste@gmail.com",null,"Comentários")
        fornecedor1.person = person2
        supplierRepository.save(fornecedor1)

        val endereco1 = Address(0,AddressType.PERSONAL,"Rua X","10",null, "bairro teste",
            "SP", "SP", "Brasil", "05303000", Instant.now(), Instant.now())
        val endereco2 = Address(0,AddressType.PROFESSIONAL,"Rua Y","20",null, "bairro teste",
                "SP", "SP", "Brasil", "05303000", Instant.now(), Instant.now())
        endereco1.person = person1
        endereco2.person = person2
        addressRepository.saveAll(listOf(endereco1, endereco2))
    }

}