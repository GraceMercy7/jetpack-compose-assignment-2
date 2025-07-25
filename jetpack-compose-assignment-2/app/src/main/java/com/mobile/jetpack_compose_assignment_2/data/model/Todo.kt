package com.mobile.jetpack_compose_assignment_2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey val id: Int,
    val title: String,
    val completed: Boolean
)
