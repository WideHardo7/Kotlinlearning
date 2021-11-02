package com.example.kotlinlearning.viewmodel
//interfaccia che viene usata per settare il numero di domande usate nell'app
interface NumeroDomande {
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

    val ndomandetot:Int
        get()=ndomandemulti+ndomandeinput
}