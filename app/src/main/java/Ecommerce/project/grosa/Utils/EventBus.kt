package Ecommerce.project.grosa.Utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

object EventBus {
    private var _event = Channel<String>()
    val event = _event.receiveAsFlow()

    fun sendEvent(event : String){
        val coroutine = CoroutineScope(Dispatchers.Default)

        coroutine.launch {
            _event.send(event)
        }

    }
}