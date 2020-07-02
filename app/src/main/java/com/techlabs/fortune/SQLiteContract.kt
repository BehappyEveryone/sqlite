package com.techlabs.fortune

import android.provider.BaseColumns

object SQLiteContract {
    // BaseColumns 인터페이스는 _ID와 _COUNT 열의 이름을 제공함
    object MemoEntry : BaseColumns {
        const val TABLE_NAME = "memo"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_CONTENT = "content"
    }
}