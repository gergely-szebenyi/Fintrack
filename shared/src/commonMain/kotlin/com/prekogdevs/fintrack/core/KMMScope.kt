package com.prekogdevs.fintrack.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

// A concrete CoroutineScope for iOS — exported to Swift as Shared.KMMScope.
// iOS creates one per ViewModel and calls cancel() in deinit.
// Android uses viewModelScope instead.
class KMMScope : CoroutineScope {
    private val job: Job = SupervisorJob()
    override val coroutineContext = Dispatchers.Main + job

    fun cancel() = cancel(null)
}
