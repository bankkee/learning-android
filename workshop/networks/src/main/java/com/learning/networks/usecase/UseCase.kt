package com.learning.networks.usecase

import com.learning.networks.model.Failure
import com.learning.networks.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * งานหนึ่งอย่าง: [run] ทำงานบน background thread (Dispatchers.IO)
 * แล้ว onResult ถูกเรียกกลับบน main thread จึงแตะ LiveData ได้ทันที
 */
abstract class UseCase<out Type : Any, in Params> : CoroutineScope {

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    abstract suspend fun run(params: Params, useCache: Boolean = true): Result<Failure, Type?>

    operator fun invoke(
        params: Params,
        useCache: Boolean = true,
        onResult: (Result<Failure, Type?>) -> Unit = {}
    ) {
        if (job.isCancelled) job = Job()
        CoroutineScope(Dispatchers.Main + job).launch {
            val deferred = async(Dispatchers.IO) {
                run(params, useCache)
            }
            val result = deferred.await()
            if (job.isCancelled) {
                return@launch
            }
            onResult(result)
        }
    }

    open fun cancel() {
        job.apply {
            cancelChildren()
            cancel()
        }
    }
}
