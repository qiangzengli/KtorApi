package com.alan.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.text.SimpleDateFormat
import java.time.LocalDateTime

val sft = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

 open class BaseEntity(name: String) : Table(name) {
    val createTime = datetime("create_time").default(LocalDateTime.now())
    val updateTime = datetime("update_time").default(LocalDateTime.now())
    val id = uuid("id").autoGenerate()
}