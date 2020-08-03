package com.sgo.sgo.entities

import com.sgo.sgo.entities.domains.PaymentMethod
import com.sgo.sgo.entities.domains.PaymentStatus
import com.sgo.sgo.entities.domains.PaymentType
import java.time.Instant
import javax.persistence.*

@Entity
class Installment(@Id
                  @GeneratedValue(strategy= GenerationType.IDENTITY)
                  val id : Long = 0,
                  val forecastDate: Instant,
                  val executionDate: Instant,
                  val billedAdmin: Double,
                  val nonBilledAdmin: Double,
                  val amount: Double,
                  val installmentNumber: Int,
                  //TODO SolPagto, ObsPagto, ObsInt, Porc, RT, ValorRT, StatusRT, DataPrevRT, DataPgRT, ObsRT, RTX, PercRTX, ValorRTX,
                  // StatusPGRTx, DataPGRTX,Imprimir, Reembolso
                  val officeName: String?
                  ) {

    @ManyToOne
    lateinit var estimate: Estimate

    @ManyToOne
    lateinit var paymentStatus: PaymentStatus

    @ManyToOne
    lateinit var paymentType: PaymentType

    @ManyToOne
    lateinit var paymentMethod: PaymentMethod

    constructor(forecastDate: Instant,
                executionDate: Instant,
                billedAdmin: Double,
                nonBilledAdmin: Double,
                amount: Double,
                installmentNumber: Int,
                officeName: String?,
                estimate: Estimate,
                paymentStatus: PaymentStatus,
                paymentType: PaymentType,
                paymentMethod: PaymentMethod) : this(forecastDate = forecastDate, executionDate = executionDate,
                                        billedAdmin = billedAdmin, nonBilledAdmin = nonBilledAdmin,
                                        amount = amount, installmentNumber = installmentNumber, officeName = officeName) {
        this.estimate = estimate
        this.paymentMethod = paymentMethod
        this.paymentStatus = paymentStatus
        this.paymentType = paymentType
    }
}