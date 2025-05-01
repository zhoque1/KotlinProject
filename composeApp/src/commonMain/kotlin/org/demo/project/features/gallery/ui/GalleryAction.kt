package org.demo.project.features.gallery.ui

interface GalleryAction {
    data class OnSearchBooks(val query: String): GalleryAction
}