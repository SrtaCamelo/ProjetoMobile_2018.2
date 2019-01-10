package ufrpe.br.visualizadoratividades.beans

class Usuario {
    var nome : String = ""
    var curso : String = ""
    var campus : String = ""
    var email : String = ""
    var senha : String = ""

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

    constructor(){}


    fun EncodeString(){
        this.email = this.email.replace(".", ",")
    }

    fun DecodeString(){
        this.email = this.email.replace(",", ".")
    }
}