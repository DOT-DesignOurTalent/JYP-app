package com.dot.jyp.model

import com.google.gson.annotations.SerializedName

data class KakaoSearchResult(
    @SerializedName("meta") val meta : Meta,
    @SerializedName("documents") val documents : List<Document>
)

data class Meta(
    @SerializedName("same_name") val same_name : String,
    @SerializedName("pageable_count") val pageable_count : Int,
    @SerializedName("total_count") val total_count : Int,
    @SerializedName("is_end") val is_end : String
)

data class Document(
    @SerializedName("place_name") val place_name : String,
    @SerializedName("distance") val distance : String,
    @SerializedName("place_url") val place_url : String,
    @SerializedName("category_name") val category_name : String,
    @SerializedName("address_name") val address_name : String,
    @SerializedName("road_address_name") val road_address_name : String,
    @SerializedName("id") val id : String,
    @SerializedName("phone") val phone : String,
    @SerializedName("category_group_code") val category_group_code : String,
    @SerializedName("category_group_name") val category_group_name : String,
    @SerializedName("x") val x : String,
    @SerializedName("y") val y : String,

)
