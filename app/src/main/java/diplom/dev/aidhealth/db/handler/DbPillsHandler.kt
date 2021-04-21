package diplom.dev.aidhealth.db.handler

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import diplom.dev.aidhealth.db.model.Pill


val DATABASE_NAME_PILL = "MyDB"
val TABLE_NAME_PILL = "Pills"
val COL_ID_PILL = "id"
val COL_PILL_TITLE = "title"
val COL_PILL_PACK = "pack"
val COL_PILL_UNITY = "unity"
val COL_PILL_MEASUREMENT="measurement"
var DATABASE_VERSION_PILL = 4

val CREATE_TABLE_PILL = "CREATE TABLE  $TABLE_NAME_PILL ("+
        "$COL_ID_PILL INTEGER PRIMARY KEY AUTOINCREMENT, $COL_PILL_TITLE TEXT, "+
        "$COL_PILL_PACK INTEFER, $COL_PILL_UNITY REAL,"+
        "$COL_PILL_MEASUREMENT TEXT)"

val SQL_DELETE_TABLE_PILL = "DROP TABLE IF EXISTS $TABLE_NAME_PILL"

class DbPillsHandler(var context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME_PILL, null,
    DATABASE_VERSION_PILL
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_PILL)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_TABLE_PILL)
        onCreate(db)
    }

    fun insertPill(pill: Pill) {

        val db = writableDatabase
        var cv = ContentValues()

        cv.put(COL_PILL_TITLE, pill.title)
        cv.put(COL_PILL_PACK, pill.pack)
        cv.put(COL_PILL_UNITY, pill.unity)
        cv.put(COL_PILL_MEASUREMENT, pill.measurement)
        var result = db.insert(TABLE_NAME_PILL, null, cv)
        if (result == -1.toLong())
          //  Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
    }

    fun readPill(): MutableList<Pill> {
        var list: MutableList<Pill> = ArrayList()
        val db = this.readableDatabase

        val query = "SELECT * FROM " + TABLE_NAME_PILL
        try{
            val result = db.rawQuery(query, null)

            if (result.moveToFirst()) {
                do {
                    var pill = Pill()
                    pill.id = result.getString(result.getColumnIndex(COL_ID_PILL)).toInt()
                    pill.title = result.getString(result.getColumnIndex(COL_PILL_TITLE))
                    pill.pack = result.getString(result.getColumnIndex(COL_PILL_PACK)).toInt()
                    pill.unity = result.getString(result.getColumnIndex(COL_PILL_UNITY)).toFloat()
                    pill.measurement = result.getString(result.getColumnIndex(COL_PILL_MEASUREMENT))
                    list.add(pill)
                } while (result.moveToNext())
            }
            result.close()
            db.close()

        }
        catch(e: Exception) {
            Toast.makeText(context, "Таблицы не существует", Toast.LENGTH_SHORT).show()
        }
        return list
    }
/*
    fun dropDoctorTable(){
        val db = this.writableDatabase
        db.execSQL("DROP TABLE " + TABLE_NAME)
        DATABASE_VERSION = DATABASE_VERSION+1
        db.close()
    }*/

}
