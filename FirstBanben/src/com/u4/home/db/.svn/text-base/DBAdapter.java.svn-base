package com.u4.home.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DBAdapter
{
	public static final String DB_ACTION = "db_action" ;//LogCat

	private static final String DB_NAME = "people.db" ;//数据库名
	private static final String DB_TABLE = "peopleinfo" ;//数据库表名
	private static final int DB_VERSION = 1 ;//数据库版本号

	public static final String KEY_ID = "_id" ; //表属性ID
	public static final String KEY_NAME = "name" ;//表属性name
	public static final String KEY_AGE = "age" ;//表属性age
	public static final String KEY_HEIGHT = "height" ;//表属性height

	private SQLiteDatabase db ;
	private Context xContext ;
	private DBOpenHelper dbOpenHelper ;
	public DBAdapter(Context context)
	{
		xContext = context ;
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
	public long insert(Jiajushiti people)
	{
		ContentValues newValues = new ContentValues();

		newValues.put(KEY_NAME, people.Name);
		newValues.put(KEY_AGE, people.Age);
		newValues.put(KEY_HEIGHT, people.Height);

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
	/**
	 * 根据id查询数据的代码
	 * @param id
	 * @return
	 */
	public Jiajushiti[] queryOneData(long id)
	{
		Cursor result = db.query(DB_TABLE, new String[] {KEY_ID,KEY_NAME,KEY_AGE,KEY_HEIGHT}, 
				KEY_ID+"="+id, null, null, null, null);
		return ConvertToPeople(result) ;
	}
	/**
	 * 查询全部数据
	 * @return
	 */
	public Jiajushiti[] queryAllData()
	{
		Cursor result = db.query(DB_TABLE, new String[] {KEY_ID,KEY_NAME,KEY_AGE,KEY_HEIGHT}, 
				null, null, null, null, null);
		return ConvertToPeople(result);
	}

	/**
	 * 根据id更新一条数据
	 */
	public long updateOneData(long id ,Jiajushiti people)
	{
		ContentValues newValues = new ContentValues();

		newValues.put(KEY_NAME, people.Name);
		newValues.put(KEY_AGE, people.Age);
		newValues.put(KEY_HEIGHT, people.Height);

		return db.update(DB_TABLE, newValues, KEY_ID+"="+id, null);
	}

	/**
	 * ConvertToPeople(Cursor cursor)是私有函数，
	 * 作用是将查询结果转换为用来存储数据自定义的People类对象
	 * People类的包含四个公共属性，分别为ID、Name、Age和Height，对应数据库中的四个属性值
	 */
	private Jiajushiti[] ConvertToPeople(Cursor cursor)
	{
		int resultCounts = cursor.getCount();
		if(resultCounts == 0 || !cursor.moveToFirst())
		{
			return null ;
		}
		Jiajushiti[] peoples = new Jiajushiti[resultCounts];
		Log.i(DB_ACTION, "PeoPle len:"+peoples.length);
		for (int i = 0; i < resultCounts; i++)
		{
			peoples[i] = new Jiajushiti();
			peoples[i].ID = cursor.getInt(0);
			peoples[i].Name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
			peoples[i].Age = cursor.getInt(cursor.getColumnIndex(KEY_AGE));
			peoples[i].Height = cursor.getFloat(cursor.getColumnIndex(KEY_HEIGHT));
			Log.i(DB_ACTION, "people "+i+"info :"+peoples[i].toString());
			cursor.moveToNext();
		}
		return peoples;
	}


	/**
	 * 静态Helper类，用于建立、更新和打开数据库
	 */
	private static class DBOpenHelper extends SQLiteOpenHelper
	{
		/*
		 * 手动创建表的SQL命令
CREATE TABLE peopleinfo
(_id integer primary key autoincrement,
name text not null,
age integer,
height float);*/
		private static final String DB_CREATE=
				"CREATE TABLE "+DB_TABLE
				+" ("+KEY_ID+" integer primary key autoincrement, "
				+KEY_NAME+" text not null, "
				+KEY_AGE+" integer,"+
				KEY_HEIGHT+" float);";
		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version)
		{
			super(context, name, factory, version);
		}

		/* 
		 * 函数在数据库第一次建立时被调用，
		 * 一般用来用来创建数据库中的表，并做适当的初始化工作
		 */
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(DB_CREATE);
			Log.i(DB_ACTION, "onCreate");
		}

		/* 
		 * SQL命令。onUpgrade()函数在数据库需要升级时被调用，
		 * 通过调用SQLiteDatabase对象的execSQL()方法，
		 * 执行创建表的一般用来删除旧的数据库表，并将数据转移到新版本的数据库表中
		 */
		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion)
		{

			//为了简单起见，并没有做任何的的数据转移，而仅仅删除原有的表后建立新的数据库表
			_db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
			onCreate(_db);
			Log.i(DB_ACTION, "Upgrade");
		}

	}
}
