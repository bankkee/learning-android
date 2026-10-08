package com.learning.core.di

interface ModuleInject {
    fun dropFeature(): Unit?
    fun injectFeature()
}
