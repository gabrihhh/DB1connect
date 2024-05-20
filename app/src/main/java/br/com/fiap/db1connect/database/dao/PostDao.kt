package br.com.fiap.db1connect.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.db1connect.model.Post
@Dao
interface PostDao {
    @Insert
    fun  salvar(post: Post): Long

    @Query("SELECT * FROM tb_post WHERE verified = true ORDER BY id")
    fun listarPosts(): List<Post>

    @Query("SELECT * FROM tb_post WHERE verified = false ORDER BY id")
    fun listarAwaitingPosts(): List<Post>

    @Update
    fun atualizarPost(post: Post): Int

    @Delete
    fun excluirPost(post: Post): Int

//    @Query
//    fun listarPostPorId(id: Long): Post

}