package com.javokhir.reachyourgoal.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModules())
    }


//using in iOS
fun initKoin() = initKoin {}