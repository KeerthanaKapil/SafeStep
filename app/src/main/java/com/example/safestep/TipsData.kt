package com.example.safestep

/**
 * Holds the content for all safety tips.
 * The indices in the titles and contents arrays are designed to correspond
 * with each other, allowing for easy lookup.
 */
object TipsData {
    /**
     * Array of titles for different safety tip categories.
     */
    val titles = arrayOf(
        "Workplace Safety",
        "Home Safety",
        "University Safety",
        "Online Safety",
        "Street Safety"
    )

    /**
     * Array of detailed content for each safety tip title.
     */
    val contents = arrayOf(
        "Be aware of your surroundings and report any suspicious activity. Keep your workspace organized to prevent accidents. Know the emergency exit routes.",
        "Lock your doors and windows. Do not leave valuables in plain sight. Install a smoke detector and check its batteries regularly. Have a fire extinguisher readily available.",
        "Never walk alone at night. Share your location with trusted friends. Be cautious about who you let into your dorm or apartment. Secure your belongings in the library.",
        "Use strong, unique passwords for your accounts. Be wary of phishing emails and suspicious links. Do not share personal information with strangers online. Keep your software updated.",
        "Stay in well-lit areas. Walk with purpose and confidence. Avoid displaying expensive items like phones or jewelry. Trust your instincts; if a situation feels unsafe, leave."
    )
}
