package com.example.androiddependencyinjection.model

data class StackOverFlowData(val items: List<Questions>)
data class Questions(
    val tags: List<String>, val owner: OwnerDetail, val is_answered: Boolean,
    val view_count: Int,
    val answer_count: Int,
    val score: Int,
    val last_activity_date: Long,
    val creation_date: Long,
    val last_edit_date: Long,
    val question_id: Long,
    val content_license: String,
    val link: String,
    val body:String,
    val title: String
)

data class OwnerDetail(
    val reputation: Int, val user_id: Long,
    val user_type: String,
    val profile_image: String,
    val display_name: String,
    val link: String
)