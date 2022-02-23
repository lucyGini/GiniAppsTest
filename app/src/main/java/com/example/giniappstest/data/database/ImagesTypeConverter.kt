package com.example.giniappstest.data.database

import androidx.room.TypeConverter
import com.example.giniappstest.data.network.PixalImages
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImagesTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(pixalImages: PixalImages): String{
        return gson.toJson(pixalImages)
    }

    @TypeConverter
    fun stringToFoodRecipe(data: String): PixalImages{
        val listType = object : TypeToken<PixalImages>(){}.type
        return gson.fromJson(data, listType)
    }
}