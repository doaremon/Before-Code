package com.u4.home.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
public class Warning_DB {
	private static final String DB_TABLE = "warning";// 数据库表名

	public static final String KEY_ID = "id"; // 编号
	public static final String KEY_TIME = "datetime";// 时间
	public static final String KEY_ZONEID = "zone_id";// 防区编号

	private DBOpenHelper dbOpenHelper;
	private Context xContext;
	private SQLiteDatabase db;

	public Warning_DB(Context xContext) {
		super();
		this.xContext = xContext;
	}

	/**
	 * 空间不够存储的时候设为只读
	 * 
	 * @throws SQLiteException
	 */
	public void open() throws SQLiteException {
		dbOpenHelper = new DBOpenHelper(xContext, Appcontext.dbName, null,
				Appcontext.dbVersion);
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

	public long insert(Warning_shitilei zone_Shitilei) {
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_ZONEID, zone_Shitilei.zone_id);
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
	public Warning_shitilei[] queryAllData() {
		Cursor result = db.query(DB_TABLE, new String[] { KEY_ID, KEY_TIME,
				KEY_ZONEID }, null, null, null, null, null);
		return ConvertToPeople(result);
	}

	private Warning_shitilei[] ConvertToPeople(Cursor cursor) {
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst()) {
			return null;
		}
		Warning_shitilei[] zone_Shitileis = new Warning_shitilei[resultCounts];
		Log.i("aaaa", "PeoPle len:" + zone_Shitileis.length);
		for (int i = 0; i < resultCounts; i++) {
			zone_Shitileis[i] = new Warning_shitilei();
			zone_Shitileis[i].id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
			zone_Shitileis[i].datetime = cursor.getString(cursor
					.getColumnIndex(KEY_TIME));
			zone_Shitileis[i].zone_id = cursor.getInt(cursor
					.getColumnIndex(KEY_ZONEID));
			cursor.moveToNext();
		}
		return zone_Shitileis;
	}

	public boolean tableIsExist(String table) {
		boolean result = false;
		if (table == null)
			return result;

		String sql = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='"
				+ table.trim() + "'";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			int count = cursor.getInt(0);
			if (count > 0)
				result = true;
		}
		return result;
	}

	public void init() {
		if (!this.tableIsExist("warning")) {
			db.execSQL("CREATE TABLE IF NOT EXISTS warning ("
					+ " id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " datetime DATETIME DEFAULT CURRENT_TIMESTAMP,"
					+ " zone_id INTEGER)");
			for (int i = 1; i <= 6; i++) {
				db.execSQL("INSERT INTO warning (zone_id) values (" + i + ")");
			}
		}
	}

	public JSONArray warningList() {
		JSONArray jsonArray = new JSONArray();

		String sql = "SELECT w.id, z.name, z.type, w.datetime"
				+ " FROM warning AS w"
				+ " LEFT JOIN zone AS z ON z.id=w.zone_id"
				+ " ORDER BY w.id DESC";

		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject
						.put("id", cursor.getInt(cursor.getColumnIndex("id")));
				jsonObject.put("name",
						cursor.getString(cursor.getColumnIndex("name")));
				jsonObject.put("type",
						cursor.getString(cursor.getColumnIndex("type")));
				jsonObject.put("datetime",
						cursor.getString(cursor.getColumnIndex("datetime")));
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return jsonArray;
	}

	public void warningAdd(int zone_id) {
		db.execSQL("INSERT INTO warning (zone_id) values (" + zone_id + ")");
	}

	public static class DBOpenHelper extends SQLiteOpenHelper {
		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
			onCreate(db);
		}
	}
}
