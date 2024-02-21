package com.example.plogger.model

import java.io.Serializable

data class JoggingRoute(
    val route: List<MarkerInfo>
): Serializable