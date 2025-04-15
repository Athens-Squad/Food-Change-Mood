package com.thechance.logic.useCases.mealSearchUseCase

class KmpMatcher(private val pattern: String) {
    private val lps: IntArray = buildLPS()

    private fun buildLPS(): IntArray {
        val lps = IntArray(pattern.length)
        var length = 0
        var i = 1
        while (i < pattern.length) {
            if (pattern[i] == pattern[length]) {
                length++
                lps[i] = length
                i++
            } else {
                if (length != 0) {
                    length = lps[length - 1]
                } else {
                    lps[i] = 0
                    i++
                }
            }
        }
        return lps
    }

    fun search(text: String): Boolean {
        var i = 0
        var j = 0
        while (i < text.length) {
            when {
                pattern[j] == text[i] -> {
                    i++
                    j++
                }
                j != 0 -> j = lps[j - 1]
                else -> i++
            }
            if (j == pattern.length) return true
        }
        return false
    }
}