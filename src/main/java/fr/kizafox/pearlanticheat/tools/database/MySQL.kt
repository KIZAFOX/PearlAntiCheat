package fr.kizafox.pearlanticheat.tools.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.lang.IllegalStateException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class MySQL {

    private val pools: ConcurrentMap<String, HikariDataSource> = ConcurrentHashMap()

    private fun custom(name: String, custom: HikariDataSource){
        val existing: HikariDataSource? = pools[name]

        if(existing != null && existing.isClosed){
            existing.close()
        }
        pools[name] = custom
    }

    fun defaultCustom(custom: HikariDataSource) = this.custom("default", custom)

    fun init(name: String?, url: String?, username: String?, password: String?): HikariDataSource{
        val config = HikariConfig()

        config.jdbcUrl = url
        config.username = username
        config.password = password
        config.addDataSourceProperty("cachePrepStmts", "true")
        config.addDataSourceProperty("prepStmtCacheSize", "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")

        val existing: HikariDataSource? = pools[name]

        if(existing != null && existing.isClosed){
            existing.close()
        }

        val hikariData = HikariDataSource(config)

        this.pools[name] = hikariData
        return hikariData
    }

    fun close(name: String){
        this.pools[name]!!.close()
    }

    fun dataSource(name: String = "default"): HikariDataSource{
        val hikariData: HikariDataSource? = pools[name]

        if(hikariData != null && !hikariData.isClosed){
            return hikariData
        }else{
            throw IllegalStateException("DataSource ($name) is absent.")
        }
    }

}