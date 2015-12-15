package com.u4.home.db;

import com.u4.home.common.Appcontext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 存储防区数据
 * 
 * @author Administrator
 * 
 */
public class Zone_DB {
	private static final String DB_TABLE = "zone";// 数据库表名

	public static final String KEY_ID = "id"; // 编号
	public static final String KEY_NAME = "name";// 名称
	public static final String KEY_TYPE = "type";// 类型
	public static final String KEY_DEFENDED = "defended";// 设防状态
	public static final String KEY_UNDEFENDED = "undefended";// 撤防状态
	public static final String KEY_STATUS = "status";// 当前状态

	private DBOpenHelper dbOpenHelper;
	private Context xContext;
	private SQLiteDatabase db;

	public Zone_DB(Context xContext) {
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

	/**
	 * 插入数据
	 * 
	 * @param moshishitilei
	 * @return
	 */

	public long insert(Zone_Shitilei zone_Shitilei) {
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_ID, zone_Shitilei.id);
		newValues.put(KEY_NAME, zone_Shitilei.name);
		newValues.put(KEY_TYPE, zone_Shitilei.type);
		newValues.put(KEY_STATUS, zone_Shitilei.status);
		newValues.put(KEY_DEFENDED, zone_Shitilei.defended);
		newValues.put(KEY_UNDEFENDED, zone_Shitilei.undefended);
		return db.insert(DB_TABLE, null, newValues);
	}

	/**
	 * 根据输入ID删除一条数据
	 * 
	 * @param id
	 * @return
	 */
	public long deleteOneData(long id) {
		return db.delete(DB_TABLE, KEY_ID + "=" + id, null);
	}

	/**
	 * 根据id更新一条数据
	 */
	public long updateOneData(long id, Zone_Shitilei shitilei) {
		ContentValues newValues = new ContentValues();

		newValues.put(KEY_NAME, shitilei.name);
		newValues.put(KEY_TYPE, shitilei.type);
		newValues.put(KEY_STATUS, shitilei.status);

		return db.update(DB_TABLE, newValues, KEY_ID + "=" + id, null);
	}

	/**
	 * 删除所有数据
	 * 
	 * @return
	 */
	public long deleteAllData() {
		return db.delete(DB_TABLE, null, null);
	}

	/**
	 * 查询全部数据
	 * 
	 * @return
	 */
	public Zone_Shitilei[] queryAllData() {
		Cursor result = db.query(DB_TABLE, new String[] { KEY_ID, KEY_NAME, KEY_TYPE, KEY_DEFENDED, KEY_UNDEFENDED, KEY_STATUS }, null, null, null, null, null);
		return ConvertToPeople(result);
	}

	private Zone_Shitilei[] ConvertToPeople(Cursor cursor) {
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst()) {
			return null;
		}
		Zone_Shitilei[] zone_Shitileis = new Zone_Shitilei[resultCounts];
		Log.i("aaaa", "PeoPle len:" + zone_Shitileis.length);
		for (int i = 0; i < resultCounts; i++) {
			zone_Shitileis[i] = new Zone_Shitilei();
			zone_Shitileis[i].id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
			zone_Shitileis[i].name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
			zone_Shitileis[i].type = cursor.getString(cursor.getColumnIndex(KEY_TYPE));
			zone_Shitileis[i].defended = cursor.getInt(cursor.getColumnIndex(KEY_DEFENDED));
			zone_Shitileis[i].undefended = cursor.getInt(cursor.getColumnIndex(KEY_UNDEFENDED));
			zone_Shitileis[i].status = cursor.getInt(cursor.getColumnIndex(KEY_STATUS));
			cursor.moveToNext();
		}
		return zone_Shitileis;
	}

	public boolean tableIsExist() {
		boolean result = false;

		String sql = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='" + DB_TABLE + "'";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			int count = cursor.getInt(0);
			if (count > 0)
				result = true;
		}
		return result;
	}

	public void initTbale() {
		db.execSQL("DELETE FROM " + DB_TABLE);
		for (int i = 1; i <= 8; i++) {
			db.execSQL("INSERT INTO " + DB_TABLE + " (" + KEY_ID + "," + KEY_NAME + "," + KEY_TYPE + "," + KEY_DEFENDED + "," + KEY_UNDEFENDED + "," + KEY_STATUS + ") values (" + i + ",'防区" + i + "','红外',0,0,0)");
		}
	}

	public void updateInfo(int id, String name, String type) {
		db.execSQL("UPDATE " + DB_TABLE + " SET " + KEY_NAME + "='" + name + "'," + KEY_TYPE + "='" + type + "' WHERE id=" + id);
	}

	public void updateDefended(int id, int defended) {
		db.execSQL("UPDATE " + DB_TABLE + " SET " + KEY_DEFENDED + "=" + defended + " WHERE id=" + id);
	}

	public void updateUndefended(int id, int undefended) {
		db.execSQL("UPDATE " + DB_TABLE + " SET " + KEY_UNDEFENDED + "=" + undefended + " WHERE id=" + id);
	}

	public void updateStatus(int id, int status) {
		db.execSQL("UPDATE " + DB_TABLE + " SET " + KEY_STATUS + "=" + status + " WHERE id=" + id);
	}

	public static class DBOpenHelper extends SQLiteOpenHelper {
		private static final String DB_CREATE = "CREATE TABLE " + DB_TABLE + " (" + KEY_ID + " INTEGER," + KEY_NAME + " VARCHAR(32) NOT NULL," + KEY_TYPE + " VARCHAR(16) NOT NULL," + KEY_DEFENDED + " INTEGER," + KEY_UNDEFENDED + " INTEGER," + KEY_STATUS + " INTEGER)";

		public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DB_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
			onCreate(db);
		}
	}
}
