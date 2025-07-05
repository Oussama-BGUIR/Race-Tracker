package com.example.racetracker.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

/**
 * State holder for a race participant.
 */
class RaceParticipant(
    val name: String,
    val maxProgress: Int = 100,
    val progressDelayMillis: Long = 500L,
    private val progressIncrement: Int = 1,
    private val initialProgress: Int = 0
) {
    init {
        require(maxProgress > 0) { "maxProgress must be greater than 0, but was $maxProgress" }
        require(progressIncrement > 0) { "progressIncrement must be greater than 0, but was $progressIncrement" }
    }

    /**
     * The participant's current progress.
     */
    var currentProgress by mutableStateOf(initialProgress)
        private set

    /**
     * Reset progress to zero regardless of initial progress.
     */
    fun reset() {
        currentProgress = 0
    }

    /**
     * Simulate running by incrementally increasing progress until maxProgress is reached.
     */
    suspend fun run() {
        while (currentProgress < maxProgress) {
            delay(progressDelayMillis)
            currentProgress += progressIncrement
        }
    }
}

/**
 * Computes the progress factor in the range 0..1 for use with LinearProgressIndicator.
 */
val RaceParticipant.progressFactor: Float
    get() = currentProgress / maxProgress.toFloat()
