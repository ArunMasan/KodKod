package com.example.bookmytravelres;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags.Description;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	public static final String DATABASE_NAME = "flightDB";

	////////////////

	// table for Create Account
	private static final String TABLE_CREATE_ACCOUNT = "table_createaccount";

	// table for booking flight
	private static final String TABLE_BOOK_FLIGHT = "table_flight";

	// table for booking hotel
	private static final String TABLE_BOOK_HOTEL = "table_hotel";

	// table for booking vocation
	private static final String TABLE_BOOK_VACATION = "table_vacation";

	// table for booking hotel name
	private static final String TABLE_BOOK_HOTEL_NAME = "table_hotel_name";

	//Columns for Create Account
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String FIRSTNAME = "firstname";
	public static final String LASTNAME = "lastname";
	public static final String MIDDLENAME = "middlename";
	public static final String ADDRESS = "address";
	public static final String EMAIL = "email";
	public static final String CARD_NUMBER = "card_number";
	public static final String CARD_MONTH = "card_month";
	public static final String CARD_YEAR = "card_year";
	public static final String CARD_BILLING_ADDR = "card_billing_addr";



	//Columns for booking flight
	public static final String BOOK_FLIGHT_SOURCE = "flight_source";
	public static final String BOOK_FLIGHT_DESTINATION = "flight_destination";
	public static final String BOOK_FLIGHT_DEPARTURE_TIME = "flight_departure_time";
	public static final String BOOK_FLIGHT_RETURN_TIME = "flight_return_time";
	public static final String BOOK_FLIGHT_TRIP_DURATION = "flight_trip_duration";
	public static final String BOOK_FLIGHT_PRICE = "flight_price";
	public static final String BOOK_FLIGHT_MILES = "flight_miles";


	//Columns for booking hotel
	public static final String BOOK_HOTEL_DESTINATION = "hotel_destination";
	public static final String BOOK_HOTEL_NAME = "hotel_name";
	public static final String BOOK_HOTEL_RATING = "hotel_rating";
	public static final String BOOK_HOTEL_PRICE = "hotel_price";


	//Columns for booking hotel name
	public static final String HOTEL_NAME = "hotell_name";
	public static final String HOTEL_TYPE = "hotell_type";
	public static final String HOTEL_PRICE = "hotell_price";

	public static String TEST = "hi";


	private Context mContext;

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		mContext = context;
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_ACCOUNT = "CREATE TABLE IF NOT EXISTS " + TABLE_CREATE_ACCOUNT + "("
				+ USERNAME + " TEXT," + PASSWORD + " TEXT,"+FIRSTNAME+" TEXT,"+LASTNAME+" TEXT,"+MIDDLENAME+" TEXT,"+ADDRESS+" TEXT,"+EMAIL+" TEXT,"
				+CARD_NUMBER+" INTEGER,"+CARD_MONTH+" INTEGER,"+CARD_YEAR+" INTEGER,"+CARD_BILLING_ADDR+" TEXT"+")";
		db.execSQL(CREATE_ACCOUNT);


		String BOOK_FLIGHT = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOK_FLIGHT + "("
				+ BOOK_FLIGHT_SOURCE + " TEXT," + BOOK_FLIGHT_DESTINATION + " TEXT,"+ BOOK_FLIGHT_DEPARTURE_TIME + " TEXT,"+ BOOK_FLIGHT_RETURN_TIME + " TEXT,"+ BOOK_FLIGHT_TRIP_DURATION + " INTEGER,"+ BOOK_FLIGHT_PRICE + " INTEGER,"+BOOK_FLIGHT_MILES+" INTEGER"+")";
		db.execSQL(BOOK_FLIGHT);

		String BOOK_HOTEL = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOK_HOTEL + "("
				+ BOOK_HOTEL_DESTINATION + " TEXT,"+ BOOK_HOTEL_NAME + " TEXT,"+ BOOK_HOTEL_PRICE + " INTEGER,"+BOOK_HOTEL_RATING + " INTEGER"+")";
		db.execSQL(BOOK_HOTEL);

		String BOOK_HOTEL_NAME = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOK_HOTEL_NAME + "("
				+HOTEL_NAME + " TEXT,"+ HOTEL_PRICE + " INTEGER,"+HOTEL_TYPE + " TEXT"+")";
		db.execSQL(BOOK_HOTEL_NAME);

		/*		String TABLE_CHAT_HISTORY_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CHAT_HISTORY + "(" +CHAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ CHAT_SENDER_ID + " INTEGER ," + CHAT_RECIEVER_ID + " INTEGER," + CHAT_DATE + " DATE,"+  CHAT_IS_READ + " INTEGER,"+  CHAT_MSG + " TEXT)";
		db.execSQL(TABLE_CHAT_HISTORY_TABLE);


		String TABLE_CREDIT_CARD_HISTORY_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CREDIT_CARD_HISTORY + "(" +CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ CARD_SUBTLE_ID + " INTEGER ," + CARD_TYPE + " TEXT)";
		db.execSQL(TABLE_CREDIT_CARD_HISTORY_TABLE);
		 */	}

	public void closeDB()
	{
		SQLiteDatabase db = getWritableDatabase();
		if(db.isOpen())
		{
			db.close();
		}
	}

	public synchronized void insertCreateAccountData(ContentValues values)
	{
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TABLE_CREATE_ACCOUNT, null, values);
		db.close();
		Toast.makeText(mContext, "Inserted", Toast.LENGTH_SHORT).show();
	}

	public synchronized void insertBookFlightData(ContentValues values)
	{
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TABLE_BOOK_FLIGHT, null, values);
		db.close();
		Toast.makeText(mContext, "Inserted", Toast.LENGTH_SHORT).show();
	}

	public synchronized void insertBookHotelData(ContentValues values)
	{
		SQLiteDatabase db = getWritableDatabase();
		db.insert(TABLE_BOOK_HOTEL, null, values);
		db.close();
		Toast.makeText(mContext, "Inserted", Toast.LENGTH_SHORT).show();
	}

	public synchronized ArrayList<BookFlightDataObject> sortByDeparture()
	{
		SQLiteDatabase db = getWritableDatabase();
		//String BOOK_FLIGHT = "SELECT * FROM " + TABLE_BOOK_FLIGHT +"ORDER BY "+SOURCE+";";
		//db.execSQL(BOOK_FLIGHT);

		String[] columns = new String[]{DatabaseHandler.BOOK_FLIGHT_SOURCE, DatabaseHandler.BOOK_FLIGHT_DESTINATION, DatabaseHandler.BOOK_FLIGHT_DEPARTURE_TIME, DatabaseHandler.BOOK_FLIGHT_RETURN_TIME, DatabaseHandler.BOOK_FLIGHT_TRIP_DURATION, DatabaseHandler.BOOK_FLIGHT_PRICE,DatabaseHandler.BOOK_FLIGHT_MILES};

		Cursor cursor =  db.query(TABLE_BOOK_FLIGHT,columns , null, null, null, null, BOOK_FLIGHT_SOURCE);


		int count = cursor.getCount();
		cursor.moveToFirst();

		//ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<BookFlightDataObject> jsonObjects = new ArrayList<BookFlightDataObject>();
		for (int i = 0; i < count; i++) {
			//if(!ids.contains(id))
			//{
			BookFlightDataObject flightDataObject = new BookFlightDataObject();

			flightDataObject.setSource(cursor.getString(cursor.getColumnIndex(BOOK_FLIGHT_SOURCE)));
			Log.e(TEST, "getSource::"+BOOK_FLIGHT_SOURCE);
			flightDataObject.setDestination(cursor.getString(cursor.getColumnIndex(BOOK_FLIGHT_DESTINATION)));
			flightDataObject.setDepartureTime( cursor.getString(cursor.getColumnIndex(BOOK_FLIGHT_DEPARTURE_TIME)));
			flightDataObject.setReturningTime( cursor.getString(cursor.getColumnIndex(BOOK_FLIGHT_RETURN_TIME)));
			flightDataObject.setTripDuration( cursor.getInt(cursor.getColumnIndex(BOOK_FLIGHT_TRIP_DURATION)));
			flightDataObject.setPrice( cursor.getInt(cursor.getColumnIndex(BOOK_FLIGHT_PRICE)));
			flightDataObject.setMiles( cursor.getInt(cursor.getColumnIndex(BOOK_FLIGHT_MILES)));

			jsonObjects.add(flightDataObject);

			//ids.add(id);

			//}
			cursor.moveToNext();

		}

		cursor.close();
		db.close();

		Toast.makeText(mContext, "sortByDeparture", Toast.LENGTH_SHORT).show();
		return jsonObjects;

	}



	public ArrayList<BookFlightDataObject> getFlightBookData(String source, String destination)
	{
		SQLiteDatabase db = getWritableDatabase();

		String[] columns = new String[]{DatabaseHandler.BOOK_FLIGHT_SOURCE, DatabaseHandler.BOOK_FLIGHT_DESTINATION, DatabaseHandler.BOOK_FLIGHT_DEPARTURE_TIME, DatabaseHandler.BOOK_FLIGHT_RETURN_TIME, DatabaseHandler.BOOK_FLIGHT_TRIP_DURATION, DatabaseHandler.BOOK_FLIGHT_PRICE,DatabaseHandler.BOOK_FLIGHT_MILES };

		//String where = BOOK_FLIGHT_SOURCE+"=? ANDÊ"+BOOK_FLIGHT_DESTINATION+"=?";
		String where = BOOK_FLIGHT_DESTINATION+"=?";
		String[] args = {destination};
		
		Cursor cursor =  db.query(TABLE_BOOK_FLIGHT,columns , where, args, null, null, null);

		int count = cursor.getCount();
		cursor.moveToFirst();

		//ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<BookFlightDataObject> jsonObjects = new ArrayList<BookFlightDataObject>();
		for (int i = 0; i < count; i++) {
			//if(!ids.contains(id))
			//{
			BookFlightDataObject flightDataObject = new BookFlightDataObject();

			flightDataObject.setSource(cursor.getString(cursor.getColumnIndex(BOOK_FLIGHT_SOURCE)));
			Log.e(TEST, "getSource::"+cursor.getString(cursor.getColumnIndex(BOOK_FLIGHT_SOURCE)));
			Log.e(TEST, "getDestination::"+cursor.getString(cursor.getColumnIndex(BOOK_FLIGHT_DESTINATION)));
			flightDataObject.setDestination(cursor.getString(cursor.getColumnIndex(BOOK_FLIGHT_DESTINATION)));
			flightDataObject.setDepartureTime( cursor.getString(cursor.getColumnIndex(BOOK_FLIGHT_DEPARTURE_TIME)));
			flightDataObject.setReturningTime( cursor.getString(cursor.getColumnIndex(BOOK_FLIGHT_RETURN_TIME)));
			flightDataObject.setTripDuration( cursor.getInt(cursor.getColumnIndex(BOOK_FLIGHT_TRIP_DURATION)));
			flightDataObject.setPrice( cursor.getInt(cursor.getColumnIndex(BOOK_FLIGHT_PRICE)));
			flightDataObject.setMiles( cursor.getInt(cursor.getColumnIndex(BOOK_FLIGHT_MILES)));

			jsonObjects.add(flightDataObject);

			//ids.add(id);

			//}
			cursor.moveToNext();

		}

		cursor.close();
		db.close();
		return jsonObjects;
	}



	public ArrayList<BookHotelDataObject> getHotelBookData()
	{
		SQLiteDatabase db = getWritableDatabase();

		String[] columns = new String[]{DatabaseHandler.BOOK_HOTEL_DESTINATION, DatabaseHandler.BOOK_HOTEL_NAME, DatabaseHandler.BOOK_HOTEL_PRICE, DatabaseHandler.BOOK_HOTEL_RATING};

		Cursor cursor =  db.query(TABLE_BOOK_HOTEL,columns , null, null, null, null, null);

		int count = cursor.getCount();
		cursor.moveToFirst();

		//ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<BookHotelDataObject> jsonObjects = new ArrayList<BookHotelDataObject>();
		for (int i = 0; i < count; i++) {
			BookHotelDataObject bookHotelDataObject = new BookHotelDataObject();

			bookHotelDataObject.setHotelDestination(cursor.getString(cursor.getColumnIndex(BOOK_HOTEL_DESTINATION)));
			Log.e(TEST, "getSource::"+BOOK_HOTEL_DESTINATION);
			bookHotelDataObject.setHotelName(cursor.getString(cursor.getColumnIndex(BOOK_HOTEL_NAME)));
			bookHotelDataObject.setHotelPrice( cursor.getInt(cursor.getColumnIndex(BOOK_HOTEL_PRICE)));
			bookHotelDataObject.setHotelRating( cursor.getInt(cursor.getColumnIndex(BOOK_HOTEL_RATING)));

			jsonObjects.add(bookHotelDataObject);

			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		return jsonObjects;
	}



	public synchronized Boolean getLoginDetails(String userName, String Pswd)
	{
		SQLiteDatabase db = getWritableDatabase();
		Cursor cursor =  db.query(TABLE_CREATE_ACCOUNT, new String[]{USERNAME,PASSWORD}, USERNAME +"= ? ", new String[]{userName}, null, null, null,null);

		if(cursor.getCount()<1) // UserName Not Exist
		{
			cursor.close();
			return false;
		}

		cursor.moveToFirst();   

		String DBUserName = cursor.getString(cursor.getColumnIndex(USERNAME));
		String DBPswd = cursor.getString(cursor.getColumnIndex(PASSWORD));

		cursor.close();
		db.close();
		//return new String[]{userName, userImg};
		if(DBUserName==null || DBPswd==null)
			return false;
		if(DBUserName.equals(DBUserName) && DBPswd.equals(Pswd))
			return true;
		else
			return false;
	}



	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	class BookFlightDataObject
	{
		String Source;
		String Destination;
		String DepartureTime;
		String ReturningTime;
		int TripDuration;
		int Price;
		int miles;
		public String getSource() {
			return Source;
		}
		public void setSource(String source) {
			Source = source;
		}
		public String getDestination() {
			return Destination;
		}
		public void setDestination(String destination) {
			Destination = destination;
		}
		public String getDepartureTime() {
			return DepartureTime;
		}
		public void setDepartureTime(String departureTime) {
			DepartureTime = departureTime;
		}
		public String getReturningTime() {
			return ReturningTime;
		}
		public void setReturningTime(String returningTime) {
			ReturningTime = returningTime;
		}
		public int getTripDuration() {
			return TripDuration;
		}
		public void setTripDuration(int tripDuration) {
			TripDuration = tripDuration;
		}
		public int getPrice() {
			return Price;
		}
		public void setPrice(int price) {
			Price = price;
		}
		public int getMiles() {
			return miles;
		}
		public void setMiles(int miles) {
			this.miles = miles;
		}
	}


	class BookHotelDataObject
	{
		String HotelDestination;
		String HotelName;
		int HotelPrice;
		int HotelRating  ;
		public String getHotelDestination() {
			return HotelDestination;
		}
		public void setHotelDestination(String hotelDestination) {
			HotelDestination = hotelDestination;
		}
		public String getHotelName() {
			return HotelName;
		}
		public void setHotelName(String hotelName) {
			HotelName = hotelName;
		}
		public int getHotelPrice() {
			return HotelPrice;
		}
		public void setHotelPrice(int hotelPrice) {
			HotelPrice = hotelPrice;
		}
		public int getHotelRating() {
			return HotelRating;
		}
		public void setHotelRating(int hotelRating) {
			HotelRating = hotelRating;
		}


	}
}
