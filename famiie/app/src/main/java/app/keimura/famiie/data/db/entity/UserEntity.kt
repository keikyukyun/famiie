package app.keimura.famiie.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * USERテーブルのEntityクラス。
 *
 * @property userId User's Unique ID.
 * @property name User's name.
 * @property gender User's gender identities.
 */
@Entity(tableName = "USER")
data class UserEntity(
        @ColumnInfo(name = "_ID")
        @PrimaryKey var userId: String = UUID.randomUUID().toString(),
        @ColumnInfo(name = "NAME")
        var name: String,
        @ColumnInfo(name = "GENDER")
        var gender: String
)