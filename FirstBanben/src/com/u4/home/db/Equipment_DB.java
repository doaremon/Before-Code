
package com.u4.home.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.u4.home.common.Appcontext;

/**
 * 存储设备数据
 * 
 * @author Administrator
 * 
 */

public class Equipment_DB {
	private SQLiteDatabase db;
	private Context xContext;
	private DBOpenHelper dbOpenHelper;

	public Equipment_DB(Context xContext) {
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

	public void equipmentAdd(String code, String unit, String name, String cmds, int type_id) {
		db.execSQL("INSERT INTO equipment (code, unit, name, cmds, type_id) VALUES ('"
				+ code + "','" + unit + "','" + name + "','" + cmds + "'," + type_id + ")");
		System.out.println("执行sql");
	}

	public void equipmentMod(int id, String code, String unit, String name, int type_id) {
		db.execSQL("UPDATE equipment SET code='" + code + "', unit='" + unit + "', name='" + name
				+ "', type_id=" + type_id + " WHERE id=" + id);
	}

	public void equipmentDel(int id) {
		db.execSQL("DELETE FROM equipment WHERE id=" + id);
	}

	public void equipmentSet(int id, int status) {
		db.execSQL("UPDATE equipment SET status=" + status + " WHERE id=" + id);
	}

	public JSONObject equipmentGet(int id) {
		JSONObject jsonObject = new JSONObject();
		if (id < 1)
			return jsonObject;

		String sql = "SELECT e.id, e.code, e.unit, e.name, e.cmds, e.status, e.type_id, t.name AS type_name"
				+ " FROM equipment AS e"
				+ " LEFT JOIN type AS t ON t.id=e.type_id"
				+ " WHERE e.id="
				+ id;
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			try {
				jsonObject.put("id", cursor.getInt(cursor.getColumnIndex("id")));
				jsonObject.put("code", cursor.getString(cursor.getColumnIndex("code")));
				jsonObject.put("unit", cursor.getString(cursor.getColumnIndex("unit")));
				jsonObject.put("name", cursor.getString(cursor.getColumnIndex("name")));
				jsonObject.put("cmds", cursor.getString(cursor.getColumnIndex("cmds")));
				jsonObject.put("status", cursor.getInt(cursor.getColumnIndex("status")));
				jsonObject.put("type_id", cursor.getInt(cursor.getColumnIndex("type_id")));
				jsonObject.put("type_name", cursor.getString(cursor.getColumnIndex("type_name")));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		cursor.close();
		return jsonObject;
	}

	public JSONArray equipmentList(int type_id) {
		
		JSONArray jsonArray = new JSONArray();
		if (type_id < 1)
			return jsonArray;

		String sql = "SELECT e.id, e.code, e.unit, e.name, e.cmds, e.status, e.type_id, t.name AS type_name"
				+ " FROM equipment AS e"
				+ " LEFT JOIN type AS t ON t.id=e.type_id"
				+ " WHERE e.type_id=" + type_id + " ORDER BY e.id ASC";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", cursor.getInt(cursor.getColumnIndex("id")));
				jsonObject.put("code", cursor.getString(cursor.getColumnIndex("code")));
				jsonObject.put("unit", cursor.getString(cursor.getColumnIndex("unit")));
				jsonObject.put("name", cursor.getString(cursor.getColumnIndex("name")));
				jsonObject.put("cmds", cursor.getString(cursor.getColumnIndex("cmds")));
				jsonObject.put("status", cursor.getInt(cursor.getColumnIndex("status")));
				jsonObject.put("type_id", cursor.getInt(cursor.getColumnIndex("type_id")));
				jsonObject.put("type_name", cursor.getString(cursor.getColumnIndex("type_name")));
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		cursor.close();
		System.out.println("执行select");
		return jsonArray;
	}
	
	public String equipmentCmds(String code) {
		String result = "";
		String sql = "SELECT GROUP_CONCAT(cmds) FROM equipment WHERE code='" + code + "'";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			result = cursor.getString(0);
		}
		return result;
	}
	
	public JSONArray typeList() {
		JSONArray jsonArray = new JSONArray();

		String sql = "SELECT id, name FROM type ORDER BY id ASC";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", cursor.getInt(cursor.getColumnIndex("id")));
				jsonObject.put("name",cursor.getString(cursor.getColumnIndex("name")));
				jsonArray.put(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		cursor.close();
		return jsonArray;
	}

	private static class DBOpenHelper extends SQLiteOpenHelper {
		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
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
