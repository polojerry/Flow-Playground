package com.samples.flow.domain.usecase

interface BaseUseCase<in Params, out T> {
    suspend operator fun invoke(params: Params): T
}