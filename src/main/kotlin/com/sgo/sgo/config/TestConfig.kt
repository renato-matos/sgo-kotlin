package com.sgo.sgo.config

import com.sgo.sgo.data.*
import com.sgo.sgo.entities.*
import com.sgo.sgo.entities.domains.*
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

    @Autowired
    lateinit var administrationTypeRepository: AdministrationTypeRepository

    @Autowired
    lateinit var contractTypeRepository: ContractTypeRepository

    @Autowired
    lateinit var projectStatusRepository: ProjectStatusRepository

    @Autowired
    lateinit var projectTypeRepository: ProjectTypeRepository

    @Autowired
    lateinit var paymentMethodRepository: PaymentMethodRepository

    @Autowired
    lateinit var paymentStatusRepository: PaymentStatusRepository

    @Autowired
    lateinit var paymentTypeRepository: PaymentTypeRepository

    override fun run(vararg args: String?) {

        val at1 = AddressType(1, "HOME")
        val at2 = AddressType(2, "PROFESSIONAL")
        val at3 = AddressType(3, "WORK")
        val at4 = AddressType(4, "BILLING")

        addressTypeRepository.saveAll(listOf(at1, at2, at3, at4))

        val adt1 = AdministrationType(0,"ADMIN TYPE 1")
        val adt2 = AdministrationType(0,"ADMIN TYPE 2")

        administrationTypeRepository.saveAll(listOf(adt1, adt2))

        val ct1 = ContractType(0,"CONTRACT TYPE 1")
        val ct2 = ContractType(0,"CONTRACT TYPE 2")

        contractTypeRepository.saveAll(listOf(ct1, ct2))

        val ps1 = ProjectStatus(0, "INITIAL ESTIMATION")
        val ps2 = ProjectStatus(0, "WIP")
        val ps3 = ProjectStatus(0, "DONE")

        projectStatusRepository.saveAll(listOf(ps1, ps2, ps3))

        val pt1 = ProjectType(0, "PROJECT TYPE 1")
        val pt2 = ProjectType(0, "PROJECT TYPE 2")

        projectTypeRepository.saveAll(listOf(pt1, pt2))

        val pm1 = PaymentMethod(0, "CASH")
        val pm2 = PaymentMethod(0, "CREDIT CARD")
        val pm3 = PaymentMethod(0, "CHECK")

        paymentMethodRepository.saveAll(listOf(pm1, pm2, pm3))

        val psc1 = PaymentStatus(0,"SCHEDULED")
        val psc2 = PaymentStatus(0,"PAID")

        paymentStatusRepository.saveAll(listOf(psc1, psc2))

        val pty1 = PaymentType(0,"PAYMENT TYPE 1")
        val pty2 = PaymentType(0,"PAYMENT TYPE 2")

        paymentTypeRepository.saveAll(listOf(pty1, pty2))

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