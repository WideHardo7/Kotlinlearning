package com.example.kotlinlearning.util
//Interfaccia che viene utilizzata per gestire il comportamento delle domande
// , che differiscono leggermente tra una tipologia e l'altra, o per gli oggetti che trattano o
// per la loro meccanica, viene utilizzata inQuizBottoneViewModel e QuizTastieraViewModel
interface GestioneDomande {




    var nrispcorrette:Int

    //mischia le domande e setta inidice a zero, in modo che verra sceltà
    // la domanda che si trova ora in posizione [0]
    fun mischiaDomande()

    //inizializza la domanda corrente e le sue relative risposte
    fun setQuestion()

    //Controlla se le domande eseguite eccedono o no il numero di domande prestabilite.
    fun checkquestionNumber():Boolean


}