package com.thechance.data

import java.io.File

class MealsFileReader(private val file: File) {

    fun readMealRecords(): List<String> {
        val records = mutableListOf<String>()
        val bufferedLines = mutableListOf<String>() // to hold the current record that may have multiple lines
        var quotesCount = 0

        try {
            file.useLines { lines ->
                //drop the header line then iterate over other lines
                lines.drop(1).forEach { line ->
                    bufferedLines.add(line) // add the line to the buffer
                    quotesCount += line.count{ it == '"'}

                    if (quotesCount % 2 == 0) { //this means that the record is balanced and each starting quote have ending quote
                        // accumulate the lines belongs to the same records and then add the record
                        records.add(bufferedLines.joinToString("\n"))
                        bufferedLines.clear()
                        quotesCount = 0
                    }
                }
            }
        } catch (e: MealsDataException) {
            throw MealsDataException.MealsFileReadingException()
        }

        return records
    }
}