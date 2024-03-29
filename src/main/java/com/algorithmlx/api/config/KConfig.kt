package com.algorithmlx.api.config

import org.jetbrains.annotations.ApiStatus

annotation class Config(
    val branch: String = "common",
    val comment: String = "Automatically generated by LVConfig system",
    val name: String = "",
    @ApiStatus.Experimental
    val range: Range = Range(enableRanges = false)
) {
    annotation class Range(
        val minInt: Int = 0,
        val maxInt: Int = 0,
        val minFloat: Float = 0F,
        val maxFloat: Float = 0F,
        val minDouble: Double = 0.0,
        val maxDouble: Double = 0.0,
        val minLong: Long = 0L,
        val maxLong: Long = 0L,
        val minByte: Byte = 0,
        val maxByte: Byte = 0,
        val minShort: Short = 0,
        val maxShort: Short = 0,

        val enableRanges: Boolean
    )
}
