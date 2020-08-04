package com.sgo.sgo.entities.domains

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class PhoneType(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                     val id : Int,
                     val description: String)