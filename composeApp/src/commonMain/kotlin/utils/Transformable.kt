package utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface Transformable<OUT> {
    fun transform() : OUT
}

fun <T> Flow<Transformable<T>>.transformCollection() : Flow<T> = map(Transformable<T>::transform)
fun <T> List<Transformable<T>>.transformCollection() : List<T> = map(Transformable<T>::transform)
