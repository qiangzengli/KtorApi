package com.alan.entity

/**
 * 用户表
 */
object User : BaseEntity("blog_user") {
    val name = varchar("name", 255)
    val password = varchar("password", 255)
    val phone = varchar("phone", 11)
}