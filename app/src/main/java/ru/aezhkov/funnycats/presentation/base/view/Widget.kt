package ru.aezhkov.funnycats.presentation.base.view

interface Widget<Model> {
    fun bind(model: Model)
}