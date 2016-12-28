package com.vsouhrada.kotlin.android.anko.fibo.core.db

import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.vsouhrada.kotlin.android.anko.fibo.lib_db.entity.CurrencyEntity
import com.vsouhrada.kotlin.android.anko.fibo.lib_db.entity.UserEntity
import io.requery.Persistable
import io.requery.android.sqlite.DatabaseSource
import io.requery.meta.EntityModel
import io.requery.sql.KotlinEntityDataStore
import java.sql.SQLException
import java.util.*

/**
 * @author vsouhrada
 * @version 0.1
 * @since 0.1
 */
class FiboDatabaseSource(context: Context, model: EntityModel, version: Int) : DatabaseSource(context, model, version) {

  lateinit var dataStore: KotlinEntityDataStore<Persistable>

  override fun onCreate(db: SQLiteDatabase?) {
    super.onCreate(db)
    insertDefaultUser()
    insertCurrencies()
  }

  override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    super.onUpgrade(db, oldVersion, newVersion)
  }

  private fun insertDefaultUser() {
    var user = UserEntity()
    with(user) {
      firstName = "Frosty"
      lastName = "Snowman"
      userName = "frosty"
      password = "snow"
    }

    dataStore.insert(user)
  }

  private fun insertCurrencies() {
    var currency = CurrencyEntity()
    with(currency) {
      code = "CZK"
      name = "Koruna"
      symbol = "CZK"
    }

    dataStore.insert(currency)
  }


  // This method is for Android DB Monitory only
  fun getData(Query: String): ArrayList<Cursor?> {
    //get writable database
    val sqlDB = this.writableDatabase
    val columns = arrayOf("mesage")
    //an array list of cursor to save two cursors one has results from the query
    //other cursor stores error message if any errors are triggered
    val alc = ArrayList<Cursor?>(2)
    val Cursor2 = MatrixCursor(columns)
    alc.add(null)
    alc.add(null)


    try {
      val maxQuery = Query
      //execute the query results will be save in Cursor c
      val c = sqlDB.rawQuery(maxQuery, null)


      //add value to cursor2
      Cursor2.addRow(arrayOf<Any>("Success"))

      alc.set(1, Cursor2)
      if (null != c && c.count > 0) {


        alc.set(0, c)
        c.moveToFirst()

        return alc
      }
      return alc
    } catch (sqlEx: SQLException) {
      Log.d("printing exception", sqlEx.message)
      //if any exceptions are triggered save the error message to cursor an return the arraylist
      Cursor2.addRow(arrayOf<Any>("" + sqlEx.message))
      alc.set(1, Cursor2)
      return alc
    } catch (ex: Exception) {

      Log.d("printing exception", ex.message)

      //if any exceptions are triggered save the error message to cursor an return the arraylist
      Cursor2.addRow(arrayOf<Any>("" + ex.message))
      alc.set(1, Cursor2)
      return alc
    }


  }
}