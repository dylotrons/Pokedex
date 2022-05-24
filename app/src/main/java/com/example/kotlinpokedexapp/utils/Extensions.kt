package com.example.kotlinpokedexapp.utils

fun String.toUpperFirstChar(): String {
    return replaceFirstChar { it.uppercase() }
}