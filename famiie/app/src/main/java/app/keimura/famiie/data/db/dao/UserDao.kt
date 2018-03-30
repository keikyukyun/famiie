package app.keimura.famiie.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import app.keimura.famiie.data.db.entity.UserEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * USERテーブルへのアクセスクラス。
 */
@Dao
abstract class UserDao {
    /**
     * データを挿入する。
     */
    @Insert
    abstract fun insertUserData(): Completable

    /**
     * データを削除する。
     */
    @Delete
    abstract fun deleteUserData(): Completable

    /**
     * データを一件取得する。
     *
     * @param userId ユーザーID
     */
    @Query("SELECT * FROM USER WHERE _ID = :userId")
    abstract fun getUserData(userId: String): Flowable<UserEntity>

    /**
     * データを複数件取得する。
     */
    @Query("SELECT * FROM USER")
    abstract fun getAllUserData(): Flowable<List<UserEntity>>
}