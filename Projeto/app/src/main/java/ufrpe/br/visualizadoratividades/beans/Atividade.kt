package ufrpe.br.visualizadoratividades.beans

class Atividade {
    var titulo : String = ""
    var descricao : String = ""
    var local : String = ""
    var horario : String = ""
    var dia : String = ""
    var tipo : String = ""
    var autor : String = ""
    var id : String = ""

    constructor() {}

    constructor(titulo : String, descricao : String, horario : String, local : String){
        this.titulo = titulo
        this.descricao = descricao
        this.horario = horario
        this.local = local
        this.tipo = ""
    }

    constructor(titulo : String, descricao : String, horario : String, local : String,
                tipo : String, dia : String, autor : String, id : String){
        this.titulo = titulo
        this.descricao = descricao
        this.horario = horario
        this.local = local
        this.tipo = tipo
        this.dia = dia
        this.autor = autor
        this.id = id
    }

}