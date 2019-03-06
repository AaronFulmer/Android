package com.fulmer.aar.login;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;


public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_Name = "videoshoppe.db";

    private static final String employeeTable = "employee";
    private static final String employeeID = "EmployeeID";            // Primary Key
    private static final String employeeFirstName = "firstname";
    private static final String employeeLastName = "lastname";
    private static final String employeeEmail = "email";
    private static final String employeePhoneNumber = "phonenumber";
    private static final String employeeUserName = "username";
    private static final String employeePassword = "password";

    private static final String customerTable = "customer";
    private static final String customerID = "CustomerID";           // Primary Key
    private static final String customerLastName = "lastname";
    private static final String customerFirstName = "firstname";
    private static final String customerEmail = "email";
    private static final String customerCardNumber = "cardNumber";   // Foreign Key
    private static final String customerPhoneNumber = "phoneNumber";

    private static final String cardTable = "card";
    private static final String cardNumber = "number";
    private static final String cardExpDate = "expirationDate";
    private static final String cardSecurityCode = "securityCode";
    private static final String cardType = "cardType";

    private static final String rentalTable = "rental";
    private static final String rentalID = "rentalID";
    private static final String rentalCustomerID = "rentalCustomerID";
    private static final String rentalUPCCode = "UPCCode";             // Foreign Key
    private static final String rentalReturnDate = "returnDate";
    private static final String rentalPrice = "price";

    private static final String dvdTable = "dvd";
    private static final String dvdUPCCode = "UPCCode";                // Primary Key
    private static final String dvdName = "name";
    private static final String dvdReleaseDate = "releaseDate";
    private static final String dvdDirector = "director";
    private static final String dvdGenre = "genre";
    private static final String dvdActors = "actors";
    private static final String dvdCondition = "condition";

    private static final String scheduleTable = "schedule";
    private static final String scheduleDateAndTime = "DateAndTime";   // Primary Key
    private static final String scheduleEmployeeId = "employeeID";     // Foreign Key
    private static final String scheduleHours = "Hours";


    DatabaseHelper(Context c){
        super(c, DB_Name, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDB) {
        sqLiteDB.execSQL("CREATE TABLE " + employeeTable + " ("
                + employeeID + " TEXT PRIMARY KEY, "
                + employeeLastName + " TEXT, "
                + employeeFirstName + " TEXT, "
                + employeeEmail + " TEXT, "
                + employeePhoneNumber + " TEXT, "
                + employeeUserName + " TEXT, "
                + employeePassword + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDB, int i, int i1){
        sqLiteDB.execSQL("DROP TABLE IF EXISTS " + employeeTable);
        onCreate(sqLiteDB);
    }


    public Cursor getName(String userName, String password){
        String[] columns = {employeeFirstName};
        String where = employeeUserName + " = ? and " + employeePassword + " = ?";
        String[] args = new String[]{userName, password};
        Cursor firstName = this.getReadableDatabase().query(employeeTable, columns, where, args, null, null, null);
        return firstName;
    }

    public String getEmployeeFirstName(){
        return employeeFirstName;
    }


    public void addDefaultUserData(){
        SQLiteDatabase sqLiteDB = this.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put(employeeID, "0001");
        c.put(employeeLastName, "Doe");
        c.put(employeeFirstName, "John");
        c.put(employeeEmail, "john.doe@gmail.com");
        c.put(employeePhoneNumber, "1234567890");
        c.put(employeeUserName, "admin");
        c.put(employeePassword, "password");
        sqLiteDB.insert(employeeTable, null, c);

        c = new ContentValues();
        c.put(employeeID, "0002");
        c.put(employeeLastName, "Fulmer");
        c.put(employeeFirstName, "Aaron");
        c.put(employeeEmail, "aar.fulmer@gmail.com");
        c.put(employeePhoneNumber, "8432452183");
        c.put(employeeUserName, "afulmer6127");
        c.put(employeePassword, "71123");
        sqLiteDB.insert(employeeTable, null, c);

        c = new ContentValues();
        c.put(employeeID, "0003");
        c.put(employeeLastName, "Animal Shelter");
        c.put(employeeFirstName, "Dummy");
        c.put(employeeEmail, "animal.shelter@gmail.com");
        c.put(employeePhoneNumber, "9876543210");
        c.put(employeeUserName, "ashelter");
        c.put(employeePassword, "nope");
        sqLiteDB.insert(employeeTable, null, c);
    }

}
