package com.sgo.sgo.entities

import java.time.Instant
import javax.persistence.*

@Entity
class Estimate (@Id
                @GeneratedValue(strategy= GenerationType.IDENTITY)
                val id : Long = 0,
                val item: String,
                val service: String,
                val serviceDescription: String,
                val insertDate: Instant,
                val requestDate: Instant?
                //TODO To be complemented with further details
                ) {

    @ManyToOne
    lateinit var supplier: Supplier

    constructor(item: String,
                service:String,
                serviceDescription: String,
                insertDate: Instant,
                requestDate: Instant?,
                supplier: Supplier) : this (item  = item, service = service, serviceDescription = serviceDescription,
                                            insertDate = insertDate, requestDate = requestDate) {
        this.supplier = supplier
    }

}