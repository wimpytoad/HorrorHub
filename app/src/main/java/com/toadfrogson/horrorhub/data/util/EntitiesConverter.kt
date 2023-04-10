package com.toadfrogson.horrorhub.data.util

import androidx.room.TypeConverter

class EntitiesConverter {
    @TypeConverter
    fun fromList(dataList: List<String>): String {
        return dataList.joinToString(separator = ",")
    }

    @TypeConverter
    fun toList(stringData: String): List<String> {
        return stringData.split(",").map { it.trim() }
    }
}