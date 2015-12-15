package com.u4.home.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
/**
 * 存储家居控制指令数据DB
 * @author Administrator
 *
 */
public class Command_DB {
	public static final String DB_ACTION = "Command_DB" ;//LogCat

	private static final String DB_NAME = "command.db" ;//数据库名
	private static final String DB_TABLE = "command" ;//数据库表名
	private static final int DB_VERSION = 1 ;//数据库版本号

	public static final String KEY_KEY = "key" ; //表属性名称
	public static final String KEY_VALUE = "value" ;//表属性内容

	private SQLiteDatabase db ;
	private Context xContext ;
	private DBOpenHelper dbOpenHelper ;

	public Command_DB(Context xContext) {
		super();
		this.xContext = xContext;
	}
	/** 空间不够存储的时候设为只读
	 * @throws SQLiteException
	 */
	public void open() throws SQLiteException
	{
		dbOpenHelper = new DBOpenHelper(xContext, DB_NAME, null,DB_VERSION);
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
	 * 向表中添加一条数据
	 * @param people
	 * @return
	 */
	public long insert(Command_Shitilei command_Shitilei )
	{
		ContentValues newValues = new ContentValues();

		newValues.put(KEY_KEY, command_Shitilei.key);
		newValues.put(KEY_VALUE,command_Shitilei.value);
		return db.insert(DB_TABLE, null, newValues);
	}
	/**
	 * 根据key查询数据的代码
	 * @param
	 * @return
	 */
	public Command_Shitilei[] queryOneData(String key)
	{
		Cursor result = db.query(DB_TABLE, new String[] {KEY_KEY,KEY_VALUE}, 
				KEY_KEY+"='"+key+"'", null, null, null, null);
		return ConvertToPeople(result) ;
	}

	/**
	 * 删除所有数据
	 * @return
	 */
	public long deleteAllData()
	{
		return db.delete(DB_TABLE, null, null);
	}
	/**
	 * 根据key更新一条数据
	 */
	public long updateOneData(String key ,Command_Shitilei command_Shitilei)
	{
		ContentValues newValues = new ContentValues();

		newValues.put(KEY_KEY,command_Shitilei.key);
		newValues.put(KEY_VALUE, command_Shitilei.value);

		return db.update(DB_TABLE, newValues, KEY_KEY+"='"+key+"'", null);
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	public Command_Shitilei[] queryAllData()
	{
		Cursor result = db.query(DB_TABLE, new String[] {KEY_KEY,KEY_VALUE}, 
				null, null, null, null, null);
		return ConvertToPeople(result);
	}
	private Command_Shitilei[] ConvertToPeople(Cursor cursor)
	{
		int resultCounts = cursor.getCount();

		if(resultCounts == 0 || !cursor.moveToFirst())
		{
			return null ;
		}
		Command_Shitilei[] command_Shitileis=new Command_Shitilei[resultCounts];
		Log.i("aaa", "camera_shitileis len:"+command_Shitileis.length);
		for (int i = 0; i < resultCounts; i++)
		{
			command_Shitileis[i] = new Command_Shitilei();
			command_Shitileis[i].key = cursor.getString(cursor.getColumnIndex(KEY_KEY));
			command_Shitileis[i].value = cursor.getString(cursor.getColumnIndex(KEY_VALUE));
			Log.i("aaa", "camera_shitileis "+i+"info :"+command_Shitileis[i].toString());
			cursor.moveToNext();
		}
		return command_Shitileis;
	}
	private static class DBOpenHelper extends SQLiteOpenHelper
	{
		private static final String DB_CREATE=
				"CREATE TABLE "+DB_TABLE
				+" ("+KEY_KEY+" text not null, "
				+KEY_VALUE+" text not null);";
		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version)
		{
			super(context, name, factory, version);
		}
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(DB_CREATE);
			Log.i(DB_ACTION, "onCreate");
		}
		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion)
		{
			_db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
			onCreate(_db);
			Log.i(DB_ACTION, "Upgrade");
		}

	}
}
