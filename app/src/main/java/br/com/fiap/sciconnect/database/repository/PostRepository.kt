package br.com.fiap.sciconnect.database.repository

import android.content.Context
import br.com.fiap.sciconnect.database.dao.PostDb
import br.com.fiap.sciconnect.model.Post

class PostRepository (context: Context){

    var db = PostDb.getDataBase(context).postDao()

    fun salvar(post: Post): Long{
        return db.salvar(post = post)
    }

    fun listarPosts(): List<Post>{
        return db.listarPosts()
    }

    fun listarAwaitingPosts(): List<Post>{
        return db.listarAwaitingPosts()
    }

    fun atualizarPost(post: Post): Int {
        return db.atualizarPost(post = post)
    }

    fun excluirPost(post: Post): Int {
        return db.excluirPost(post = post)
    }


}