package me.brisson.note_app.domain.model

import java.util.Calendar
import java.util.UUID

data class Note(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    val createdAt: Long = Calendar.getInstance().timeInMillis,
    var content: String
)
