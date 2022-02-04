package com.clothex.user.home.categories.style

enum class DepartmentEnum(val value: String) {
    MEN("men"), WOMEN("women"), KIDS("kids")
}

object DepartmentFactory {
    fun getDepartmentStyle(department: DepartmentEnum): DepartmentStyle {
        return when (department) {
            DepartmentEnum.MEN -> DepartmentStyle.Men
            DepartmentEnum.WOMEN -> DepartmentStyle.Women
            DepartmentEnum.KIDS -> DepartmentStyle.Kids
        }
    }
}