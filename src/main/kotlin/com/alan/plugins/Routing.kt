package com.alan.plugins

import com.alan.entity.User
import com.alan.entity.User.name
import com.alan.entity.User.password
import com.alan.entity.User.phone
import com.alan.req.RegisterUserReq
import com.alan.resp.UserInfoResp
import com.alan.resp.respFail
import com.alan.resp.respOk
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {
    routing {
        // 注册接口
        post("/register") {
            val registerUseReq = call.receive<RegisterUserReq>()
            println("注册用户信息:$registerUseReq")
            if (registerUseReq.phone.isEmpty()) {
                call.respond(respFail("手机号不能为空"))
            } else if (registerUseReq.password.isEmpty()) {
                call.respond(respFail("密码不能为空"))
            } else {
                var count = 0

                transaction {
                    val insertStatement = User.insert {
                        it[name] = registerUseReq.name
                        it[password] = registerUseReq.password
                        it[phone] = registerUseReq.phone
                    }
                    count = insertStatement.insertedCount
                }

                if (count > 0) {
                    call.respond(respOk<Nothing>("注册成功"))
                } else {
                    call.respond(respFail("注册失败"))
                }
            }

        }
        // 登录
        post("/login") {
            val registerUseReq = call.receive<RegisterUserReq>()
            println("登录用户:${registerUseReq}")
            var userInfoResp: UserInfoResp? = null
            transaction {
                val userList = User.select {
                    name eq registerUseReq.name and (password eq registerUseReq.password)
                }
                val resultRow = userList.single()
                userInfoResp = UserInfoResp(resultRow[phone], resultRow[name], resultRow[password])
            }
            call.respond(respOk(data = userInfoResp))
        }
    }
}

