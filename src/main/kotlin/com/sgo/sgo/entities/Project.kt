package com.sgo.sgo.entities

import com.sgo.sgo.entities.domains.*
import org.springframework.data.geo.Point
import java.time.Instant
import javax.persistence.*

class Project(@Id
              @GeneratedValue(strategy= GenerationType.IDENTITY)
              val id : Long = 0,
              val startDate: Instant?,
              val endDate: Instant?,
              val workStartDate: Instant?,
              val workEndDate: Instant?,
              val area: Double,
              //TODO Compatibilização ??? Boxes???? Outra data de início? ConfirDevContrato? PorcentModif
              val initialDepositDate: Instant?,
              val latitude: Double,
              val longitude: Double) {

    @ManyToOne
    lateinit var projectStatus: ProjectStatus

    @ManyToOne
    lateinit var projectType: ProjectType

    @ManyToOne
    lateinit var contractType: ContractType

    @ManyToOne
    lateinit var administrationType: AdministrationType

    @OneToMany(mappedBy = "project")
    lateinit var workAdresses: List<WorkAddress>

    constructor(startDate: Instant?,
                endDate: Instant?,
                workStartDate: Instant?,
                workEndDate: Instant?,
                area: Double,
                initialDepositDate: Instant?,
                latitude: Double,
                longitude: Double,
                projectStatus: ProjectStatus,
                projectType: ProjectType,
                contractType: ContractType,
                administrationType: AdministrationType) : this (startDate = startDate,
                                                                endDate = endDate,
                                                                workStartDate = workStartDate,
                                                                workEndDate = workEndDate,
                                                                area = area,
                                                                initialDepositDate = initialDepositDate,
                                                                latitude = latitude,
                                                                longitude = longitude) {
        this.projectStatus = projectStatus
        this.projectType = projectType
        this.contractType = contractType
        this.administrationType = administrationType
    }

}