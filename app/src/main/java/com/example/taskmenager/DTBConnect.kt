package com.example.taskmenager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DTBConnect(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object
    {
        internal const val DATABASE_NAME = "Tasks_DB"
        internal const val DATABASE_VERSION = 2
        internal const val TABLE_NAME = "Taska_Table"
        internal const val COL_ID = "Id"
        internal const val COL_NAME = "Name"
        internal const val COL_PRIORITY ="Priority"
        internal const val COL_STATUS = "Status"
        internal const val COL_DATE = "Date"
        internal const val COL_DESCRIPTION = "Description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = db!!.execSQL("CREATE TABLE $TABLE_NAME( " +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_NAME TEXT," +
                "$COL_PRIORITY TEXT," +
                "$COL_STATUS TEXT," +
                "$COL_DATE TEXT," +
                "$COL_DESCRIPTION TEXT)"
        )

       /* val values = ContentValues()
        values.put(COL_NAME, "zadanie2")
        values.put(COL_PRIORITY, "wazne")
        values.put(COL_STATUS, "Tod")
        values.put(COL_DATE, "20.11.2019")
        values.put(COL_DESCRIPTION, "abc")

        //val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
       // db.close()*/
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    //adding single task to database
    fun addTaskToDTB(task: Task) {
        val values = ContentValues()
        values.put(COL_NAME, task.taskName)
        values.put(COL_PRIORITY, task.taskPriority)
        values.put(COL_STATUS, task.taskStatus)
        values.put(COL_DATE, task.taskDate)
        values.put(COL_DESCRIPTION, task.taskDescription)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun delTaskFromDTB(id_tasku: Int){

        val db = this.writableDatabase
        db!!.execSQL("DELETE FROM $TABLE_NAME WHERE $COL_ID = \"$id_tasku\" ")
        db.close()
    }

    fun getIdFromDTB(task: Task):Int
    {
        var date = task.taskDate
        var desc = task.taskDescription
        var id = 0
        /*val db = this.readableDatabase
        val queryID = db.rawQuery("SELECT $COL_ID FROM $TABLE_NAME WHERE $COL_DATE = \"$date\" AND $COL_DESCRIPTION = \"$desc\" ",null)

        return queryID.toString()*/
        val query = "SELECT $COL_ID FROM $TABLE_NAME WHERE $COL_DATE = \"$date\" AND $COL_DESCRIPTION = \"$desc\" "

        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null,null)

        while(cursor.moveToNext())
        {
            val task_id = Integer.parseInt(cursor.getString(0))
            id = task_id
        }

        cursor.close()
        db.close()
        return id
    }

    fun getTaskFromDTB(): MutableList<Task>
    {
        val query = "SELECT * FROM $TABLE_NAME ORDER BY $COL_PRIORITY DESC"

        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null,null)

        val taskslist = mutableListOf<Task>()

        while(cursor.moveToNext())
        {
            val task_id = Integer.parseInt(cursor.getString(0))
            val task_name = (cursor.getString(1))
            val task_priority = (cursor.getString(2))
            val task_status = (cursor.getString(3))
            val task_date = (cursor.getString(4))
            val task_description = (cursor.getString(5))

            val task= Task(task_name, task_priority, task_status,task_date,task_description)

            taskslist.add(task)
        }

        cursor.close()
        db.close()
        return taskslist
    }
}