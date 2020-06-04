package io.a.b.c

import io.fritz2.lenses.Lenses

@Lenses
data class Person(val name: String, val age: Int, val address: Address)

@Lenses
data class Address(val street: String, val numbers: List<Int>)