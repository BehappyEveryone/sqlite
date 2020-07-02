package com.techlabs.fortune

import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var dbHelper: SQLiteDBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        dbHelper = SQLiteDBHelper(this)
        saveDatabaseButton.setOnClickListener(this)
        getDatabaseButton.setOnClickListener(this)
        deleteDatabaseButton.setOnClickListener(this)
        getAllDatabaseButton.setOnClickListener(this)
        deleteAllDatabaseButton.setOnClickListener(this)
        modifyDatabaseButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.saveDatabaseButton -> {
                val title = memo_title.text.toString()
                val content = memo_content.text.toString()
                saveSQLiteData(title, content)
            }
            R.id.getDatabaseButton -> {
                val title = memo_title.text.toString()
                val content = memo_content.text.toString()
                getSQLiteData(title, content)
            }
            R.id.deleteDatabaseButton -> {
                val title = memo_title.text.toString()
                val content = memo_content.text.toString()
                deleteSQLiteData(title, content)
            }
            R.id.getAllDatabaseButton -> {
                getAllSQLiteData()
            }
            R.id.deleteAllDatabaseButton -> {
                deleteAllSQLiteData()
            }
            R.id.modifyDatabaseButton -> {
                val title = memo_title.text.toString()
                val content = memo_content.text.toString()
                val modifyTitle = modify_memo_title.text.toString()
                val modifyContent = modify_memo_content.text.toString()
                modifySQLiteData(title,content,modifyTitle,modifyContent)
            }
        }
    }

    private fun saveSQLiteData(title: String, content: String) {
        // Gets the data repository in write mode
        val db = dbHelper?.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(SQLiteContract.MemoEntry.COLUMN_NAME_TITLE, title)
            put(SQLiteContract.MemoEntry.COLUMN_NAME_CONTENT, content)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(SQLiteContract.MemoEntry.TABLE_NAME, null, values)

        if (newRowId?.toInt() == -1) {
            Toast.makeText(this, "오류", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "정상작동", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSQLiteData(title: String, content: String) {
        sql_content.text = ""
        val projection = arrayOf(
            BaseColumns._ID,
            SQLiteContract.MemoEntry.COLUMN_NAME_TITLE,
            SQLiteContract.MemoEntry.COLUMN_NAME_CONTENT
        )

        val db = dbHelper?.readableDatabase
        val sortOrder = "${BaseColumns._ID} DESC"
        val selection = "${SQLiteContract.MemoEntry.COLUMN_NAME_TITLE} = ? AND ${SQLiteContract.MemoEntry.COLUMN_NAME_CONTENT} = ?"
        val selectionArgs = arrayOf(title,content)

        val cursor = db?.query(
            SQLiteContract.MemoEntry.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,             // The array of columns to return (pass null to get all) ,              // The columns for the WHERE clause
            selectionArgs,             // The array of columns to return (pass null to get all) ,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder             // The array of columns to return (pass null to get all)
            // The sort order
        )

        var queryIdx:Int? = null
        var queryTitle:String? = null
        var queryContent:String? = null

        cursor?.let {
            while (it.moveToNext()){
                queryIdx = it.getInt(0)
                queryTitle = it.getString(1)
                queryContent = it.getString(2)
            }
        }

        sql_content.text = "idx: $queryIdx , 제목: $queryTitle , 내용: $queryContent"
    }

    private fun deleteAllSQLiteData() {
        sql_content.text = ""
        val db = dbHelper?.writableDatabase
        val deletedRows = db?.delete(SQLiteContract.MemoEntry.TABLE_NAME, null, null)

        sql_content.text = "삭제된 행 수 : $deletedRows"
    }

    private fun deleteSQLiteData(title:String,content: String) {
        sql_content.text = ""
        val db = dbHelper?.writableDatabase

        // Define 'where' part of query.
        val selection = "${SQLiteContract.MemoEntry.COLUMN_NAME_TITLE} LIKE ? AND ${SQLiteContract.MemoEntry.COLUMN_NAME_CONTENT} LIKE ?"
        val selectionArgs = arrayOf(title,content)
        val deletedRows = db?.delete(SQLiteContract.MemoEntry.TABLE_NAME, selection, selectionArgs)

        sql_content.text = "삭제된 행 수 : $deletedRows"
    }

    private fun modifySQLiteData(title: String, content: String,modifyTitle:String,modifyContent:String) {
        val db = dbHelper?.writableDatabase

        // New value for one column
        val values = ContentValues().apply {
            put(SQLiteContract.MemoEntry.COLUMN_NAME_TITLE, modifyTitle)
            put(SQLiteContract.MemoEntry.COLUMN_NAME_CONTENT, modifyContent)
        }

        // Which row to update, based on the title
        val selection = "${SQLiteContract.MemoEntry.COLUMN_NAME_TITLE} LIKE ? AND ${SQLiteContract.MemoEntry.COLUMN_NAME_CONTENT} LIKE ?"
        val selectionArgs = arrayOf(title,content)
        val count = db?.update(
            SQLiteContract.MemoEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs)
        sql_content.text = "수정된 행 수 : $count"
    }

    private fun getAllSQLiteData() {
        sql_content.text = ""
        val db = dbHelper?.readableDatabase
        val sortOrder = "${BaseColumns._ID} DESC"

        val cursor = db?.query(
            SQLiteContract.MemoEntry.TABLE_NAME,   // The table to query
            null,             // The array of columns to return (pass null to get all)
            null,             // The array of columns to return (pass null to get all) ,              // The columns for the WHERE clause
            null,             // The array of columns to return (pass null to get all) ,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder             // The array of columns to return (pass null to get all)
            // The sort order
        )

        var queryIdx:Int? = null
        var queryTitle:String? = null
        var queryContent:String? = null

        cursor?.let {
            while (it.moveToNext()){
                val existContent = sql_content.text

                queryIdx = it.getInt(0)
                queryTitle = it.getString(1)
                queryContent = it.getString(2)

                sql_content.text = "$existContent idx: $queryIdx , 제목: $queryTitle , 내용: $queryContent\n"
            }
        }
    }
}
