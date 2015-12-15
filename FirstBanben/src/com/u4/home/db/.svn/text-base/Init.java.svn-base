package com.u4.home.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.u4.home.common.Appcontext;

public class Init {
	private DBOpenHelper dbOpenHelper;
	private Context xContext;
	private SQLiteDatabase db;

	public Init(Context xContext) {
		super();
		this.xContext = xContext;
	}

	/**
	 * 空间不够存储的时候设为只读
	 * 
	 * @throws SQLiteException
	 */
	public void open() throws SQLiteException {
		dbOpenHelper = new DBOpenHelper(xContext, Appcontext.dbName, null, Appcontext.dbVersion);
		try {
			db = dbOpenHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			db = dbOpenHelper.getReadableDatabase();
		}
	}

	/**
	 * 调用SQLiteDatabase对象的close()方法关闭数据库
	 */
	public void close() {
		if (db != null) {
			db.close();
			db = null;
		}
	}

	public boolean tableIsExist(String table) {
		boolean result = false;
		if (table == null)
			return result;

		String sql = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='" + table.trim() + "'";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			int count = cursor.getInt(0);
			if (count > 0)
				result = true;
		}
		return result;
	}

	public void init() {
		if (!this.tableIsExist("type")) {
			db.execSQL("CREATE TABLE IF NOT EXISTS type (" + "id INTEGER PRIMARY KEY, " + "pid INTEGER DEFAULT 0, " + "name VARCHAR(16) NOT NULL)");
			db.execSQL("INSERT INTO type (id,name) values (1,'灯光')");
			db.execSQL("INSERT INTO type (id,name) values (2,'开关')");
			db.execSQL("INSERT INTO type (id,name) values (3,'窗帘')");
		}
		if (!this.tableIsExist("equipment")) {
			db.execSQL("CREATE TABLE IF NOT EXISTS equipment (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "code VARCHAR(32) NOT NULL, " + "unit VARCHAR(2) NOT NULL, " + "name VARCHAR(16) NOT NULL, " + "cmds VARCHAR(32) , " + "status INTEGER DEFAULT 0, " + "type_id INTEGER)");
		}
		if (!this.tableIsExist("warning")) {
			db.execSQL("CREATE TABLE IF NOT EXISTS warning (" + " id INTEGER PRIMARY KEY AUTOINCREMENT," + " datetime DATETIME DEFAULT CURRENT_TIMESTAMP," + " zone_id INTEGER)");
			for (int i = 1; i <= 6; i++) { // test data
				db.execSQL("INSERT INTO warning (zone_id) values (" + i + ")");
			}
		}
		if (!this.tableIsExist("zone")) {
			db.execSQL("CREATE TABLE zone (" + " id INTEGER PRIMARY KEY," + " name VARCHAR(32) NOT NULL," + " type VARCHAR(16) NOT NULL," + " defended INTEGER," + " undefended INTEGER," + " status INTEGER)");
			for (int i = 1; i <= 8; i++) {
				db.execSQL("INSERT INTO zone (id, name, type, defended, undefended, status) values (" + i + ",'防区" + i + "','红外',0,0,0)");
			}
		}
		if (!this.tableIsExist("theme")) {
			db.execSQL("CREATE TABLE theme (" + " id INTEGER PRIMARY KEY AUTOINCREMENT," + " name VARCHAR(32) NOT NULL, " + " equipment TEXT NOT NULL)");
		}
	}

	public static class DBOpenHelper extends SQLiteOpenHelper {
		public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}
}
