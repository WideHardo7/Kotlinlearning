package com.example.kotlinlearning.viewmodel
//Interfaccia che viene utilizzata per gestire il comportamento delle domande
// , che differiscono leggermente tra una tipologia e l'altra, o per gli oggetti che trattano o
// per la loro meccanica, viene utilizzata inQuizBottoneViewModel e QuizTastieraViewModel
interface GestioneDomande {


    var nrispcorrette:Int


    fun mischiaDomande()
    fun setQuestion()
    fun checkquestionNumber():Boolean


}