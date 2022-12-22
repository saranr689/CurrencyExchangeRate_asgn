package com.assignment.currenciesrate.model

import java.math.BigDecimal

data class CurrencyCustomModelList(
    val currencyCode: String,
    val currencyValue: BigDecimal
)