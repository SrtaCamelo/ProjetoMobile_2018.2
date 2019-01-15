package ufrpe.br.visualizadoratividades.beans

import java.util.*
import kotlin.collections.ArrayList

class Usuario {
    var nome : String = ""
    var curso : String = ""
    var campus : String = ""
    var email : String = ""
    var senha : String = ""
    var favoritos : ArrayList<String> = ArrayList()

    constructor(nome: String, curso: String, campus: String, email: String, senha: String) {
        this.nome = nome
        this.curso = curso
        this.campus = campus
        this.email = email
        this.senha = senha
    }

    constructor(email: String, senha: String) {
        this.email = email
        this.senha = senha
    }

    constructor(email: String){
        this.email = email
    }

    constructor(){}


    fun EncodeString(){
        this.email = this.email.replace(".", ",")
    }

    fun DecodeString(){
        this.email = this.email.replace(",", ".")
    }

    fun addFavorito(atividade : String){
        favoritos.add(atividade)
    }

    fun removeFavorito(atividade: String){
        favoritos.remove(atividade)
    }

    fun getFavorito() : ArrayList<String>{
        return favoritos
    }
}