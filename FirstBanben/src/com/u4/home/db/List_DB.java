package com.u4.home.db;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * 允许和静止列表DB
 * @author Administrator
 *
 */
public class List_DB {
	private static final String DB_NAME = "smart" ;//数据库名
	private static final String DB_TABLE = "yunxuliebiaochaxun" ;//数据库表名
	private static final int DB_VERSION = 1 ;//数据库版本号

	public static final String KEY_ID = "_id" ; //表属性ID自动
	public static final String KEY_NAME = "type" ;//表属性type类型
	public static final String KEY_MUBIAOMING = "target" ;//表属性target目标名称
	public static final String KEY_shijian = "datetime" ;//表属性datetime时间
	private DBOpenHelper dbOpenHelper ;
	private Context xContext ;
	private SQLiteDatabase db ;

	public List_DB(Context xContext) {
		super();
		this.xContext = xContext;
	}

	/** 空间不够存储的时候设为只读
	 * @throws SQLiteException
	 * DB_NAME数据库名字
	 * DB_VERSION版本号
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
	public long insert(List_Shitilei yunxuliebiaoshiti)
	{
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_NAME, yunxuliebiaoshiti.type);
		newValues.put(KEY_MUBIAOMING, yunxuliebiaoshiti.target);
		newValues.put(KEY_shijian, yunxuliebiaoshiti.datetime);
		return db.insert(DB_TABLE, null, newValues);
	}

	/**
	 * 根据输入ID删除一条数据
	 * @param id
	 * @return
	 * DB_TABLE表名
	 */
	public long deleteOneData(long id)
	{
		return db.delete(DB_TABLE, KEY_ID+"="+id, null );
	}
	/**
	 * 查询全部数据
	 * @return
	 */
	public List_Shitilei[] queryAllData()
	{
		Cursor result = db.query(DB_TABLE, new String[] {KEY_ID,KEY_NAME,KEY_MUBIAOMING,KEY_shijian}, 
				null, null, null, null, null);
		return ConvertToPeople(result);
	}
	private List_Shitilei[] ConvertToPeople(Cursor cursor)
	{
		int resultCounts = cursor.getCount();

		if(resultCounts == 0 || !cursor.moveToFirst())
		{
			return null ;
		}
		List_Shitilei[] list_Shitileis=new List_Shitilei[resultCounts];
		Log.i("aaa", "yunxuliebiaoshitis len:"+list_Shitileis.length);
		for (int i = 0; i < resultCounts; i++)
		{
			list_Shitileis[i] = new List_Shitilei();
			list_Shitileis[i].ID = cursor.getInt(0);
			list_Shitileis[i].type = cursor.getInt(cursor.getColumnIndex(KEY_NAME));
			list_Shitileis[i].target = cursor.getString(cursor.getColumnIndex(KEY_MUBIAOMING));
			list_Shitileis[i].datetime = cursor.getString(cursor.getColumnIndex(KEY_shijian));
			Log.i("aaa", "people "+i+"info :"+list_Shitileis[i].toString());
			cursor.moveToNext();
		}
		return list_Shitileis;
	}
	private static class DBOpenHelper extends SQLiteOpenHelper
	{
		private static final String DB_CREATE=
				"CREATE TABLE "+DB_TABLE
				+" ("+KEY_ID+" integer primary key autoincrement, "
				+KEY_NAME+" integer, "
				+KEY_MUBIAOMING+" text not null,"+
				KEY_shijian+" text not null);";
		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version)
		{
			super(context, name, factory, version);
		}
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(DB_CREATE);
		}
		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion)
		{
			_db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
			onCreate(_db);
		}

	}
}
