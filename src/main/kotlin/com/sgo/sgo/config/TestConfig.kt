package com.sgo.sgo.config

import com.sgo.sgo.data.*
import com.sgo.sgo.entities.*
import com.sgo.sgo.entities.domains.AddressType
import com.sgo.sgo.entities.domains.EntityType
import com.sgo.sgo.entities.domains.PersonType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import java.time.Instant

@Configuration
class TestConfig : CommandLineRunner {

    @Autowired
    lateinit var clientRepository: ClientRepository

    @Autowired
    lateinit var supplierRepository: SupplierRepository

    @Autowired
    lateinit var addressRepository: AddressRepository

    @Autowired
    lateinit var personAddressRepository: PersonAddressRepository

    @Autowired
    lateinit var personRepository: PersonRepository

    @Autowired
    lateinit var addressTypeRepository: AddressTypeRepository

    override fun run(vararg args: String?) {

        val at1 = AddressType(1, "HOME")
        val at2 = AddressType(2, "PROFESSIONAL")
        val at3 = AddressType(3, "WORK")

        addressTypeRepository.saveAll(listOf(at1, at2, at3))

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

        val address1 = Address("Rua X","10",null, "bairro teste",
            "SP", "SP", "Brasil", "05303000", at1)
        val address2 = Address("Rua Y","20",null, "bairro teste",
                "SP", "SP", "Brasil", "05303000", at1)

        addressRepository.saveAll(listOf(address1, address2))

        val pa1 = PersonAddress(address1)
        val pa2 = PersonAddress(address2)

        pa1.person = person1
        pa2.person = person2
        personAddressRepository.saveAll(listOf(pa1, pa2))
    }

}