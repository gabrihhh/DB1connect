package br.com.fiap.db1connect.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate.now

@Entity(tableName = "tb_post")
data class Post(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var user: String = "admin",
    var verified: Boolean = false,
    var data: String = now().toString(),
    var titulo: String = "",
    var disciplina: String = "",
    var descricao: String = ""
)
