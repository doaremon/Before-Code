package com.u4.home.db;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.u4.home.common.Appcontext;
/**
 * 情景模式
 * @author Administrator
 *
 */
public class Theme_DB {
	private static final String DB_TABLE = "theme" ;//数据库表名

	public static final String KEY_ID = "_id" ; //表属性ID
	public static final String KEY_NAME = "name" ;//表属性name
	public static final String KEY_EQUIPMENT = "equipment" ;//表属性
	private DBOpenHelper dbOpenHelper ;
	private Context xContext ;
	private SQLiteDatabase db ;



	public Theme_DB(Context xContext) {
		super();
		this.xContext = xContext;
	}
	/** 空间不够存储的时候设为只读
	 * @throws SQLiteException
	 */
	public void open() throws SQLiteException
	{
		dbOpenHelper = new DBOpenHelper(xContext,  Appcontext.dbName, null,Appcontext.dbVersion);
		try
		{
			db = dbOpenHelper.getWritableDatabase();
		}
		catch (SQLiteException e)
		{
			db = dbOpenHelper.getReadableDatabase();
		}
	}

	/**
	 * 调用SQLiteDatabase对象的close()方法关闭数据库
	 */
	public void close()
	{
		if(db != null)
		{
			db.close();
			db = null;
		}
	}
	/**
	 * 插入数据
	 * @param moshishitilei
	 * @return
	 */

	public long insert(Theme_Shitilei moshishitilei){
		ContentValues newValues = new ContentValues(); 
		newValues.put(KEY_NAME, moshishitilei.Name);
		newValues.put(KEY_EQUIPMENT, moshishitilei.equipment);
		return db.insert(DB_TABLE, null, newValues);
	}
	/**
	 * 根据输入ID删除一条数据
	 * @param id
	 * @return
	 */
	public long deleteOneData(long id)
	{
		return db.delete(DB_TABLE, KEY_ID+"="+id, null );
	}

	/**
	 * 删除所有数据
	 * @return
	 */
	public long deleteAllData()
	{
		return db.delete(DB_TABLE, null, null);
	}

	public void themeAdd(String name, String equipment) {
		db.execSQL("INSERT INTO theme (name, equipment) values ('" + name + "','" + equipment + "')");
	}

	public void themeDel(int id) {
		db.execSQL("DELETE FROM theme WHERE id=" + id);
	}

	public JSONArray themeList() {
		JSONArray jsonArray = new JSONArray();
		JSONObject row;

		String sql = "SELECT id, name, equipment"
				+ " FROM theme"
				+ " ORDER BY id ASC";
		Cursor cursor = db.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			try {
				row = new JSONObject();
				row.put("id", cursor.getInt(cursor.getColumnIndex("id")));
				row.put("name", cursor.getString(cursor.getColumnIndex("name")));
				row.put("equipment", cursor.getString(cursor.getColumnIndex("equipment")));
				jsonArray.put(row);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		cursor.close();
		return jsonArray;
	}

	public static class DBOpenHelper extends SQLiteOpenHelper{
		public DBOpenHelper(Context context, String name, CursorFactory factory,
				int version) {
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
