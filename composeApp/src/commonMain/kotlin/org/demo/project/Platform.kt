package org.demo.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform