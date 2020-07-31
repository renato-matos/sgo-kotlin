package com.sgo.sgo.entities

import com.sgo.sgo.entities.domains.AddressType
import javax.persistence.*

@Entity
data class Address (@Id @GeneratedValue(strategy = GenerationType.AUTO)
                    val id : Long = 0,
                    val street: String,
                    val number: String,
                    val complement: String?,
                    val neighborhood: String,
                    val city: String,
                    val state: String,
                    val country: String,
                    val zipCode: String) {

    @ManyToOne
    lateinit var addressType: AddressType

    constructor (street: String,
                 number: String,
                 complement: String?,
                 neighborhood: String,
                 city: String,
                 state: String,
                 country: String,
                 zipCode: String,
                 addressType: AddressType) : this(street = street, number=number, complement = complement,
                                                neighborhood = neighborhood, city = city, state = state,
                                                country = country, zipCode = zipCode) {
        this.addressType = addressType
    }

}
