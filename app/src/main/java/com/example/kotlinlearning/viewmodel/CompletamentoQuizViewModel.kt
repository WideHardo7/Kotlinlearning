package com.example.kotlinlearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinlearning.database.AppDatabase
import com.example.kotlinlearning.database.argomenti.Argument
import com.example.kotlinlearning.repository.ArgumentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompletamentoQuizViewModel(application: Application): AndroidViewModel(application), NumeroDomande {

    val repository: ArgumentRepository

    //numero di domande totali proposte all'utente
    override val ndomandetot: Int
        get() = super.ndomandetot
    //lista di tutti gli oggetti argument presenti nel database
    var listadiargomenti: List<Argument> = listOf<Argument>()



    init {
        val argumentdao = AppDatabase.getInstance(application).argumentDao()
        repository = ArgumentRepository(argumentdao)
        viewModelScope.launch {
            getTuttiArgomenti()
        }

    }

   suspend fun getTuttiArgomenti() {
       listadiargomenti=repository.getAllArgumentwithCoroutine()
       Log.d("CompletamentViewModel","La lista di oggetti argument è stata estratta con successo, eccola :$listadiargomenti")
    }

    //query che aggiorna l'oggetto Argument relativo nel database
    fun insertArgument(argomento: Argument) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertArgument(argomento)
        }
    }

    //prende un oggetto Argument in base al nome dell'argomento passato
    fun getArgumentbyNameArgument(nomeargomento: String): LiveData<Argument> {
        return repository.getArgument(nomeargomento)
    }

    //Controlla che il numero delle risposte esatte sia maggiore della metà del numero delle domande effettuate
    fun checkResult(nrisp: Int, ndom: Int) = nrisp >= ndom / 2

    //Se il numero delle domande è dispari ingrementa di 1 il numero di domande per avere una metà intera, senza resto
    // , in questo modo verifica se l'utente ha passato il test oppure no
    fun isPassed(nrisp: Int) =
        if ((ndomandetot % 2) == 0) checkResult(nrisp, ndomandetot) else checkResult(
            nrisp,
            ndomandetot + 1
        )
     //Funzione che aggiorna il database se si verificano determinate condizioni
    fun insertNewValue(nrisp: Int, argomento: Argument, argomenti: List<Argument>) {

        //oggetto che viene inizializzo con gli stessi valori dell'argomento corrente passato,come id e cod_argomento
        // per poterlo sostituire; ma poi viene eventualmente modificato in score, per inserire il nuovo  punteggio
        val newargument: Argument =
            Argument(argomento.cod_argomento, argomento.indice, argomento.score, argomento.unlocked)


         //se l'utente è passato, controllo se il punteggio è migliorato o se l'elemento sucessivo è da sbloccare,
         // se entrambe le condizioni sopra citate sono false, non fa nulla
        if (isPassed(nrisp)) {

            //ogetto che conterrà i valori dell'argomento successivo a questo,
            // per esempio se l'argomento corrente è "Variabili", il successivo sarà "Stringhe" e serve per
            // sbloccare eventualmete, l'argomento successivo, settando unlocked a true
            var nextargument:Argument= chooseNextArgument(argomento, argomenti)

            //se una di queste condizioni non si verifica allora
            if(!(nextargument.unlocked==1  && argomento.score > nrisp)){
                //se l'argomento successivo non è già sbloccato, cambia unlocked e poi lo aggiorna nel database
                if(nextargument.unlocked==0) {
                    nextargument.unlocked=1
                    insertArgument(nextargument)
                    Log.i("CompletamentoQuizViewM", "L'utente ha passato il test, quindi sblocco l'argomento successivo,aggiorno database")
                }
                //se il punteggio dell'argomento corrente nel database è minore del risultato ottenuto,
                // aggiorna il punteggio e modifica il database
                if(argomento.score < nrisp){
                    newargument.score=nrisp
                    insertArgument(newargument)
                    Log.i("CompletamentoQuizViewM", "L'utente ha passato il test, ed ha migliorato il punteggio, aggiorno database")

                }
            }

        } else {
            //se l'utente non ha passato il test, controllo comunque se ha eseguito un punteggio migliore di quello precedente,
            // se è così aggiorno il database altrimenti no
            if (argomento.score < nrisp) {
                newargument.score = nrisp
                insertArgument(newargument)
                Log.i("CompletamentoQuizViewM", "L'utente non ha passato il test, ma ha migliorato il punteggio,aggiorno database")
            }
        }
    }

    fun chooseNextArgument(argomento:Argument, argomenti:List<Argument>): Argument{
        //filtro la lista per ottenere l'elemento che ha l'indice di argomento incrementatato di uno(il successivo a quello passato),
        // in questo modo, il filtraggio trova una solo elemento, che sarà quindi il primo e lo restituisco
        val a= argomenti.filter { it.indice == argomento.indice+1 }
        Log.d("CompletamQuizViewModel", "il risultato del filtraggio della lista è: $a")
        return(a[0])

    }
}




