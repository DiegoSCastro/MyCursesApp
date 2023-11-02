package com.example.mycursesapp

import androidx.annotation.DrawableRes

data class Course(
    val rating: Float,
    val title: String,
    @DrawableRes val thumbnail: Int,
    val body: String,
)

val course1 = Course(
    4.5f,
    "The Complete Android 13 Developer Course - Build 52 Apps",
    R.drawable.course1,
    "Learn Android App Development from Zero to Hero - Build 50+ Apps from scratch - Become a real developer"
)

val course2 = Course(
    4.5f,
    "Mastering Android App Development with Kotlin -Build 38 Apps",
    R.drawable.course2,
    "Learn Android Kotlin from Zero to Hero - Build apps with MVVM, Retrofit, ROOM DB, Firebase, Jetpack, MySQL, JSON & More"
)
val course3 = Course(
    4.7f,
    "Mastering Design Patterns - Become a Professional Developer",
    R.drawable.course3,
    "Become a Professional Software Engineer, Excel in University Exams & Succeed in the Coding Interviews"
)

val course4 = Course(
    4.6f,
    "The Complete Java Developer Course -Mastering Java from zero",
    R.drawable.course4,
    "Learn Java from Zero to Hero. Become a Computer Programmer and start building complex projects. Earn Java Certification"
)
val course5 = Course(
    4.4f,
    "The Complete Firebase & Android Course - Mastering Firebase",
    R.drawable.course5,
    "Master Firebase Products from Zero to Hero"
)
val course6 = Course(
    4.8f,
    "The Complete Android JetPack Course - Mastering Jetpack",
    R.drawable.course6,
    "Become a Professional Android Developer by Mastering ROOM, MVVM, Retrofit, Navigation, WorkMgr, Dagger, Paging3 & More.."
)
val course7 = Course(
    4.6f,
    "The Complete Data Structures & Algorithms Course in C & Java",
    R.drawable.course7,
    "Master the Coding Interview: Data Structures + Algorithms with fully animated videos and over 108 real life problems"
)

val course8 = Course(
    4.4f,
    "The Complete Kotlin Course - Mastering Kotlin from Zero",
    R.drawable.course8,
    "Mastering Kotlin from scratch and Enhancing your coding career prospects"
)

val allCourses = listOf(
    course1,
    course2,
    course3,
    course4,
    course5,
    course6,
    course7,
    course8
)
