package com.example.stopwatch.model

class TimestampProviderImp : TimestampProvider {
    override fun getMilliseconds(): Long = System.currentTimeMillis()
}