package com.dmitry.pisarevskiy.hatgame.data.model

enum class Category(val title: String, val color: Int) {
    FOOD("Еда", 0xFF0000),
    SCIENCE("Наука", 0x00FF00),
    MORE_THEN_18("Клубничка", 0x0000FF),
    SPORT("Спорт", 0x0000FF),
    HISTORY("История", 0x0000FF),
    ARTS("Искусство", 0x0000FF),
    CARS("Машины", 0x0000FF),
    OTHERS("Разное", 0x0000FF),
}