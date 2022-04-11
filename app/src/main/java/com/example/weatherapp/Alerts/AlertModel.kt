package com.example.weatherApp.Alert

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "alarmTable")
data class AlertModel(
    @ColumnInfo(name = "DateFrom")
    val DateFrom: String,
    @ColumnInfo(name = "TimeFrom")
    val TimeFrom: String,
    @ColumnInfo(name = "DateTO")
    val DateTO: String,
    @ColumnInfo(name = "TimeTo")
    val TimeTo: String,
    val timeZone: String,
    var  lat:Double,
    var  lon:Double,

    @ColumnInfo(name = "requestCode")
    val requestCode: Int

) {
    @PrimaryKey(autoGenerate = true)
    var alarmId: Int = 0
}


