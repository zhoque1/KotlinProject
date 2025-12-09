package org.demo.project.di

import org.koin.core.qualifier.named

object Qualifiers {
    val JSON_PLACEHOLDER_CLIENT = named("JsonPlaceholderClient")
    val OTHER_API_CLIENT = named("OtherApiClient")
}