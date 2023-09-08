package univ.suwon.sulasang.domain.embbeded

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Company(

    @Column(name = "company_name", nullable = true)
    val companyName: String?
)