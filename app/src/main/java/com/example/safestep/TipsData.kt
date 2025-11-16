package com.example.safestep.tips
/**
 * Central data source for all safety tips used in the application.
 *
 * TipsData stores static Tip objects for all five safety categories and
 * provides helper functions for retrieving the appropriate Tip object using a
 * TIP_KEY. This keeps tip content centralized and separate from UI logic.
 *
 * Each Tip contains a title and multi-paragraph text content.
 */
object TipsData {

    val titles = listOf(
        "Safety at Work",
        "Safety at Home",
        "Safety at University",
        "Women Safety Online",
        "Safety on the Streets"
    )

    val contents = listOf(
        "Work safety tips text…",
        "Home safety tips text…",
        "University safety tips text…",
        "Women online safety tips text…",
        "Street safety tips text…"
    )
}

