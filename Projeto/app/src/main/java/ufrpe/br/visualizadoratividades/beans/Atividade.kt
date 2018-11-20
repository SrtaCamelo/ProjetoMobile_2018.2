package ufrpe.br.visualizadoratividades.beans

import java.time.LocalDateTime
import java.util.*

class Atividade {
    var titulo : String = ""
    var descricao : String = ""
    var local : String = ""
    var horario : String = ""

    constructor() {}

    constructor(titulo : String, descricao : String, horario : String, local : String){
        this.titulo = titulo
        this.descricao = descricao
        this.horario = horario
        this.local = local
    }

}