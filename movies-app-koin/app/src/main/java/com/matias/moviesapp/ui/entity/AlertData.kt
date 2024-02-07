package com.matias.moviesapp.ui.entity

class AlertData(
    val title: String,
    val message: String,
    val positiveAction: Pair<String, () -> Unit> = Pair("") { },
    val negativeAction: Pair<String, () -> Unit> = Pair("") { },
    val dismissAction: () -> Unit = { }
)
