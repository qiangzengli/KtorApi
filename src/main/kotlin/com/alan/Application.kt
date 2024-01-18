package com.alan

import com.alan.entity.User
import com.alan.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    connectDatabase()
    embeddedServer(Netty, port = 10000, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureSockets()
    configureHTTP()
    configureRouting()
}


fun connectDatabase() {
    //     链接数据库
    Database.connect(
        url = "jdbc:mysql://127.0.0.1:3306/study?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
        driver = "com.mysql.jdbc.Driver",
        user = "root",
        password = "alanalan"
    )

    transaction {
        // 日志打印
        addLogger(StdOutSqlLogger)
        // 需要创建的表进行添加
        SchemaUtils.create(User)
    }
}


