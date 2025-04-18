package com.thechance.data

import java.io.File

class MealsFileReader(private val file: File) {

    fun readFileLines(): List<String> {
        return file.readLines().drop(1)
    }
}