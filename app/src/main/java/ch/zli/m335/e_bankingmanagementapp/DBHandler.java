package ch.zli.m335.e_bankingmanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

	private static final String DB_NAME = "User.db";
	private String TABLE_NAME2 = "balance_table";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "monthly";
	private static final String ID_COL = "id";
	private static final String NAME_COL = "name";
	private static final String DURATION_COL = "duration";
	private static final String DESCRIPTION_COL = "description";
	private static final String TRACKS_COL = "tracks";

	public DBHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String query = "CREATE TABLE " + TABLE_NAME + " ("
				+ ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ NAME_COL + " TEXT,"
				+ DURATION_COL + " TEXT,"
				+ DESCRIPTION_COL + " TEXT,"
				+ TRACKS_COL + " TEXT)";

		db.execSQL(query);

		db.execSQL("create table " + TABLE_NAME2 + " (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
		db.execSQL("insert into balance_table values(1234567890,'Rohan',100.00,'rohan2@gmail.com','XXXXXXXXXXXX1234','ABC09876543')");
	}

	public void addNewMonthly(String monthlyName, String monthlyDuration, String monthlyDescription, String monthlyTracks) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(NAME_COL, monthlyName);
		values.put(DURATION_COL, monthlyDuration);
		values.put(DESCRIPTION_COL, monthlyDescription);
		values.put(TRACKS_COL, monthlyTracks);

		db.insert(TABLE_NAME, null, values);

		db.close();
	}

	public ArrayList<MonthlyPlanService> readMonthly() {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

		ArrayList<MonthlyPlanService> courseModalArrayList = new ArrayList<>();

		if (cursorCourses.moveToFirst()) {
			do {
				courseModalArrayList.add(new MonthlyPlanService(cursorCourses.getString(1),
											cursorCourses.getString(4),
											cursorCourses.getString(2),
											cursorCourses.getString(3)));
			} while (cursorCourses.moveToNext());
		}
		cursorCourses.close();
		return courseModalArrayList;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// this method is called to check if the table exists already.
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
		onCreate(db);
	}

	public Cursor readalldata(){
		SQLiteDatabase db = this.getReadableDatabase();
		db.execSQL("create table " + TABLE_NAME2 + " (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
		db.execSQL("insert into balance_table values(1234567890,'Rohan',100.00,'rohan2@gmail.com','XXXXXXXXXXXX1234','ABC09876543')");
		Cursor cursor = db.rawQuery("select * from balance_table", null);
		return cursor;
	}

}
