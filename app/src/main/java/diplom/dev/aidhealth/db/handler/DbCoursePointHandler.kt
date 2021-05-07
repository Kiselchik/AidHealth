package diplom.dev.aidhealth.db.handler

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import diplom.dev.aidhealth.DataRecyclerCourse
import diplom.dev.aidhealth.db.model.Course
import diplom.dev.aidhealth.db.model.CourseHistory
import diplom.dev.aidhealth.db.model.CoursePoint
import diplom.dev.aidhealth.db.model.StatusPoint


val TABLE_NAME_COURSE_POINT = "CoursePoint"
val COL_COURSE_POINT_ID ="id"
val COL_COURSE_POINT_courseID = "courseID"
val COL_COURSE_POINT_DAY = "day"
val COL_COURSE_POINT_TIME = "time"
val COL_COURSE_POINT_STATUS = "statusPointID"


val CREATE_TABLE_COURSE_POINT = "CREATE TABLE IF NOT EXISTS   $TABLE_NAME_COURSE_POINT ("+
        "$COL_COURSE_POINT_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_COURSE_POINT_courseID INTEGER ,$COL_COURSE_POINT_DAY TEXT, " +
        "$COL_COURSE_POINT_TIME TEXT, $COL_COURSE_POINT_STATUS INTEGER, " +
        " FOREIGN KEY(statusPointID) REFERENCES StatusPoint(id) )"

val SQL_DELETE_TABLE_COURSE_POINT = "DROP TABLE IF EXISTS ${TABLE_NAME_COURSE_POINT}"

class DbCoursePointHandler(var context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME, null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_COURSE_POINT)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_TABLE_COURSE_POINT)
        onCreate(db)
    }

    fun clearTableCoursePoint() {
        val db = writableDatabase
        db.execSQL("DELETE FROM ${TABLE_NAME_COURSE_POINT}")

    }

    fun insertCoursePoint(coursePoint: CoursePoint) {

        val db = writableDatabase
        db.execSQL(CREATE_TABLE_COURSE_POINT)

        var cv = ContentValues()

        cv.put(COL_COURSE_POINT_courseID, coursePoint.courseID)
        cv.put(COL_COURSE_POINT_DAY, coursePoint.day)
        cv.put(COL_COURSE_POINT_TIME, coursePoint.time)
        cv.put(COL_COURSE_POINT_STATUS, coursePoint.statusPointID)


        var result = db.insert(TABLE_NAME_COURSE_POINT, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "FAILED coursePoint", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "SUCCESS coursePoint", Toast.LENGTH_SHORT).show()
    }

    fun readCourseHPoint(): MutableList<CoursePoint> {
        var list: MutableList<CoursePoint> = ArrayList()
        val db = this.readableDatabase

        val query = "SELECT * FROM " + TABLE_NAME_COURSE_POINT
        try {
            val result = db.rawQuery(query, null)

            if (result.moveToFirst()) {
                do {
                    var coursePoint = CoursePoint()
                    coursePoint.courseID = result.getString(result.getColumnIndex(COL_COURSE_POINT_courseID)).toInt()
                    coursePoint.id =
                        result.getString(result.getColumnIndex(COL_COURSE_POINT_ID)).toInt()
                    coursePoint.day = result.getString(result.getColumnIndex(COL_COURSE_POINT_DAY))
                    coursePoint.time =
                        result.getString(result.getColumnIndex(COL_COURSE_POINT_TIME))
                    coursePoint.statusPointID =
                        result.getString(result.getColumnIndex(COL_COURSE_POINT_STATUS)).toInt()
                    list.add(coursePoint)
                } while (result.moveToNext())
            }
            result.close()
            db.close()

        } catch (e: Exception) {
            Toast.makeText(context, "Таблицы не существует", Toast.LENGTH_SHORT).show()
        }
        return list
    }




    fun getOneCourse(): MutableList<CoursePoint> {
        var list: MutableList<CoursePoint> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME_COURSE_POINT + " " +
               "WHERE ${COL_COURSE_POINT_courseID} = ${DataRecyclerCourse.courseID} "
     //   val query = "SELECT * FROM " + TABLE_NAME_COURSE_POINT
        try{
            val result = db.rawQuery(query, null)

            if (result.moveToFirst()) {
                do {
                    var coursePoint = CoursePoint()
                    coursePoint.courseID = result.getString(result.getColumnIndex(COL_COURSE_POINT_courseID)).toInt()
                    coursePoint.id =
                        result.getString(result.getColumnIndex(COL_COURSE_POINT_ID)).toInt()
                    coursePoint.day = result.getString(result.getColumnIndex(COL_COURSE_POINT_DAY))
                    coursePoint.id =
                        result.getString(result.getColumnIndex(COL_COURSE_POINT_ID)).toInt()
                    coursePoint.day = result.getString(result.getColumnIndex(COL_COURSE_POINT_DAY))
                    coursePoint.time =
                        result.getString(result.getColumnIndex(COL_COURSE_POINT_TIME))
                    coursePoint.statusPointID =
                        result.getString(result.getColumnIndex(COL_COURSE_POINT_STATUS)).toInt()

                    list.add(coursePoint)
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
}
/*
    fun dropDoctorTable(){
        val db = this.writableDatabase
        db.execSQL("DROP TABLE " + TABLE_NAME)
        DATABASE_VERSION = DATABASE_VERSION+1
        db.close()
    }*/