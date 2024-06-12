package utils

suspend fun <T, R> T.letCoroutine(block: suspend (T) -> R) : R {
    return block(this)
}