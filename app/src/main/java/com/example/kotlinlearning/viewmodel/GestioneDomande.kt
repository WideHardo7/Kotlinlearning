package com.example.kotlinlearning.viewmodel
//Interfaccia che viene utilizzata per gestire il comportamento delle domande
// , che differiscono leggermente tra una tipologia e l'altra, o per gli oggetti che trattano o
// per la loro meccanica, viene utilizzata inQuizBottoneViewModel e QuizTastieraViewModel
interface GestioneDomande {
    //si è deciso di avere 4 domande di questo tipo,
    // ma è possibile modificare il numero per incrementare  il numero di domande da visualizzare,
    // una volta che si è incrementato il numero di domande nel database
    val ndomandemulti:Int
    get()= 4
    //si è deciso di avere 1 domanda di questo tipo,
    // ma è possibile modificare il numero per incrementare  il numero di domande da visualizzare,
    // una volta che si èincrementato il numero di domande nel database
    val ndomandeinput
    get()=1

    val domandetot:Int
    get()=ndomandemulti+ndomandeinput



    var nrispcorrette:Int


    fun mischiaDomande()
    fun setQuestion()
    fun checkquestionNumber():Boolean


}