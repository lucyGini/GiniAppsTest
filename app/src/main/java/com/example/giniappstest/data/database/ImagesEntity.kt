package com.example.giniappstest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.giniappstest.data.network.PixalImages
import com.example.giniappstest.util.Constants.Companion.IMAGES_TABLE

@Entity(tableName = IMAGES_TABLE)
class ImagesEntity(
    var pixalImages: PixalImages) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}