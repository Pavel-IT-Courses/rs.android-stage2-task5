package com.example.finalproject

import javax.inject.Inject

class Starks @Inject constructor() : House {
    override fun prepareForWar() {
        println(javaClass.simpleName + " prepared for war")
    }

    override fun reportForWar() {
        println(javaClass.simpleName + " reporting")
    }
}
