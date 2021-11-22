package ch.zli.m335.e_bankingmanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

	// creating a constant variables for our database.
	// below variable is for our database name.
	private static final String DB_NAME = "User.db";

	private String TABLE_NAME2 = "user_table";

	private String TABLE_NAME3 = "transfers_table";

	// below int is our database version
	private static final int DB_VERSION = 1;

	// below variable is for our table name.
	private static final String TABLE_NAME = "monthly";

	// below variable is for our id column.
	private static final String ID_COL = "id";

	// below variable is for our course name column
	private static final String NAME_COL = "name";

	// below variable id for our course duration column.
	private static final String DURATION_COL = "duration";

	// below variable for our course description column.
	private static final String DESCRIPTION_COL = "description";

	// below variable is for our course tracks column.
	private static final String TRACKS_COL = "tracks";

	// creating a constructor for our database handler.
	public DBHandler(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	// below method is for creating a database by running a sqlite query
	@Override
	public void onCreate(SQLiteDatabase db) {
		// on below line we are creating
		// an sqlite query and we are
		// setting our column names
		// along with their data types.
		String query = "CREATE TABLE " + TABLE_NAME + " ("
				+ ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ NAME_COL + " TEXT,"
				+ DURATION_COL + " TEXT,"
				+ DESCRIPTION_COL + " TEXT,"
				+ TRACKS_COL + " TEXT)";

		// at last we are calling a exec sql
		// method to execute above sql query
		db.execSQL(query);

		db.execSQL("create table " + TABLE_NAME2 +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
		db.execSQL("create table " + TABLE_NAME3 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
		db.execSQL("insert into user_table values(1234567890,'Rohan',9472.00,'rohan2@gmail.com','XXXXXXXXXXXX1234','ABC09876543')");
		db.execSQL("insert into user_table values(2345678901,'Sunny',582.67,'sunny3@gmail.com','XXXXXXXXXXXX2341','BCA98765432')");
		db.execSQL("insert into user_table values(3456789012,'Mehul',1359.56,'mehul4@gmail.com','XXXXXXXXXXXX3412','CAB87654321')");
		db.execSQL("insert into user_table values(4567890123,'Amisha',1500.01,'amisha5@gmail.com','XXXXXXXXXXXX4123','ABC76543210')");
		db.execSQL("insert into user_table values(5678901234,'Hina',2603.48,'hina6@gmail.com','XXXXXXXXXXXX2345','BCA65432109')");
		db.execSQL("insert into user_table values(6789012345,'Gungun',945.16,'gungun7@gmail.com','XXXXXXXXXXXX3452','CAB54321098')");
		db.execSQL("insert into user_table values(7890123456,'Ganesh',5936.00,'ganesh8@gmail.com','XXXXXXXXXXXX4523','ABC43210987')");
		db.execSQL("insert into user_table values(8901234567,'Jony',857.22,'jony9@gmail.com','XXXXXXXXXXXX5234','BCA32109876')");
		db.execSQL("insert into user_table values(9012345678,'Urvi',4398.46,'urvi10@gmail.com','XXXXXXXXXXXX3456','CAB21098765')");
		db.execSQL("insert into user_table values(1234567809,'Tanuj',273.90,'tanuj1@gmail.com','XXXXXXXXXXXX4563','ABC10987654')");
	}

	// this method is use to add new course to our sqlite database.
	public void addNewMonthly(String monthlyName, String monthlyDuration, String monthlyDescription, String monthlyTracks) {

		// on below line we are creating a variable for
		// our sqlite database and calling writable method
		// as we are writing data in our database.
		SQLiteDatabase db = this.getWritableDatabase();

		// on below line we are creating a
		// variable for content values.
		ContentValues values = new ContentValues();

		// on below line we are passing all values
		// along with its key and value pair.
		values.put(NAME_COL, monthlyName);
		values.put(DURATION_COL, monthlyDuration);
		values.put(DESCRIPTION_COL, monthlyDescription);
		values.put(TRACKS_COL, monthlyTracks);

		// after adding all values we are passing
		// content values to our table.
		db.insert(TABLE_NAME, null, values);

		// at last we are closing our
		// database after adding database.
		db.close();
	}

	// we have created a new method for reading all the courses.
	public ArrayList<MonthlyPlanService> readMonthly() {
		// on below line we are creating a
		// database for reading our database.
		SQLiteDatabase db = this.getReadableDatabase();

		// on below line we are creating a cursor with query to read data from database.
		Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

		// on below line we are creating a new array list.
		ArrayList<MonthlyPlanService> courseModalArrayList = new ArrayList<>();

		// moving our cursor to first position.
		if (cursorCourses.moveToFirst()) {
			do {
				// on below line we are adding the data from cursor to our array list.
				courseModalArrayList.add(new MonthlyPlanService(cursorCourses.getString(1),
											cursorCourses.getString(4),
											cursorCourses.getString(2),
											cursorCourses.getString(3)));
			} while (cursorCourses.moveToNext());
			// moving our cursor to next.
		}
		// at last closing our cursor
		// and returning our array list.
		cursorCourses.close();
		return courseModalArrayList;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// this method is called to check if the table exists already.
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
		onCreate(db);
	}

	public Cursor readalldata(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from user_table", null);
		return cursor;
	}

	public Cursor readparticulardata(String phonenumber){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
		return cursor;
	}

	public Cursor readselectuserdata(String phonenumber) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
		return cursor;
	}

	public void updateAmount(String phonenumber, String amount){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
	}

	public Cursor readtransferdata(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from transfers_table", null);
		return cursor;
	}

	public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("DATE", date);
		contentValues.put("FROMNAME", from_name);
		contentValues.put("TONAME", to_name);
		contentValues.put("AMOUNT", amount);
		contentValues.put("STATUS", status);
		Long result = db.insert(TABLE_NAME3, null, contentValues);
		if(result == -1){
			return false;
		}else{
			return true;
		}
	}
}
