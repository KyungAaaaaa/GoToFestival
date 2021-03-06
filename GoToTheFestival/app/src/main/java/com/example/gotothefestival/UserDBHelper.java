package com.example.gotothefestival;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gotothefestival.Model.ThemeData;
import com.example.gotothefestival.Model.User;

import java.util.ArrayList;


public class UserDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "GoToTheFestivalDB";
    private static final int VERSION = 1;
    private static UserDBHelper userDBHelper = null;
    private ThemeData data = new ThemeData();

    private UserDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    public static UserDBHelper getInstance(Context context) {
        if (userDBHelper == null) {
            userDBHelper = new UserDBHelper(context);
        }
        return userDBHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table userTBL(" +
                "userId TEXT not null primary key," +
                "userName TEXT not null," +
                "profileImage TEXT not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists userTBL");
        onCreate(sqLiteDatabase);
    }

    public void insertUserInfo(User user) {
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.execSQL("INSERT or REPLACE INTO userTBL values('" +
                    user.getUserId() + "','" + user.getUserName() + "','" + user.getProfileImage() + "');");
            Log.d("insertUserInfo", "성공");
        } catch (SQLException e) {
            Log.d("insertUserInfo", e.getMessage());
        } finally {
            if (sqLiteDatabase != null) sqLiteDatabase.close();
        }

    }


    public void createUserLikePlaceTBL(User user) {
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + "LikePlace" + user.getUserId() + "TBL(title TEXT primary key, addr TEXT,mapX REAL,mapY REAL,image TEXT);");
            Log.d("DBcreateUserLikeTBL", "성공");
        } catch (SQLException e) {
            Log.d("DBcreateUserLikeTBL", e.getMessage());
        } finally {
            if (sqLiteDatabase != null) sqLiteDatabase.close();
        }

    }

    public ArrayList<String> likeLoad(User user) {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        try {
            sqLiteDatabase = this.getWritableDatabase();
            cursor = sqLiteDatabase.rawQuery("SELECT title FROM LikePlace" + user.getUserId() + "TBL;", null);
            while (cursor.moveToNext()) {
                arrayList.add(cursor.getString(0));
            }
            Log.d("DBLikeLoad", "성공");
        } catch (SQLException e) {
            Log.d("DBLikeLoad", e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            if (sqLiteDatabase != null) sqLiteDatabase.close();
        }
        return arrayList;
    }

    public void likeDelete(User user, ThemeData.Item data) {
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.execSQL("DELETE FROM LikePlace" + user.getUserId() + "TBL WHERE title='" + data.getTitle() + "';");
            Log.d("DBLikeDelete", "성공");
        } catch (SQLException e) {
            Log.d("DBLikeDelete", e.getMessage());
        } finally {
            if (sqLiteDatabase != null) sqLiteDatabase.close();
        }

    }

    public void likeInsert(User user, ThemeData.Item data) {
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.execSQL("INSERT INTO LikePlace" + user.getUserId() + "TBL (title,addr,mapX,mapY,image) VALUES ('"
                    + data.getTitle() + "','"
                    + data.getAddr1() + "','"
                    + data.getMapx() + "','"
                    + data.getMapy() + "','"
                    + data.getFirstimage() + "');");
            Log.d("DBLikeInsert", "성공");
        } catch (SQLException e) {
            Log.d("DBLikeInsert", e.getMessage());
        } finally {
            if (sqLiteDatabase != null) sqLiteDatabase.close();
        }

    }

    public ArrayList<ThemeData.Item> likePlaceLoad(User user) {
        ArrayList<ThemeData.Item> arrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        try {
            sqLiteDatabase = this.getWritableDatabase();
            cursor = sqLiteDatabase.rawQuery("SELECT * FROM LikePlace" + user.getUserId() + "TBL;", null);
            while (cursor.moveToNext()) {
                arrayList.add(data.new Item(cursor.getString(0), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getString(4)));
            }
            Log.d("DBLikePlaceLoad", "성공");
        } catch (SQLException e) {
            Log.d("DBLikePlaceLoad", e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            if (sqLiteDatabase != null) sqLiteDatabase.close();
        }
        return arrayList;
    }

    //카메라앨범에 사진을 저장하기위한 테이블
    public void onCreatCamereTBL(User user) {
        SQLiteDatabase sqLiteDatabase = null;

        sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL("create table if not exists ALBUM" + user.getUserId() + "TBL(" +
                    "datanum integer primary key autoincrement, " +
                    "title Text, " +
                    "firstImage Text," +
                    "addr Text, " +
                    "picture Text, " +
                    "content_pola Text, " +
                    "content_title Text, " +
                    "contents Text)");

            Log.d("DBCreatCamereTBL", "성공");
        } catch (Exception e) {
            Log.d("DBCreatCamereTBL", e.getMessage());

        } finally {
            if (sqLiteDatabase != null) sqLiteDatabase.close();
        }
    }

    //카메라 탭에서 버튼이벤트를 사용했을때 테이블에 저장하기위한 테이블
    public void oninsertCameraTBL(User user, ThemeData.Item themeData) {
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = this.getWritableDatabase();
        try {
            sqLiteDatabase.execSQL("insert into ALBUM" + user.getUserId() + "TBL Values (null,'" + themeData.getTitle() + "'," +
                    "'" + themeData.getFirstimage() + "','" + themeData.getAddr1() + "'," +
                    "'" + themeData.getPicture() + "','" + themeData.getContent_pola() + "','" + themeData.getContent_title() + "'," +
                    "'" + themeData.getContents() + "');");
            Log.d("DBinsertCameraTBL", "성공");
        } catch (Exception e) {
            Log.d("DBinsertCameraTBL", "실패");
        } finally {
            if (sqLiteDatabase != null) sqLiteDatabase.close();
        }
    }

    //저장된 사진을 불러오기
    public ArrayList<ThemeData.Item> onSelectAlbumTBL(User user) {
        SQLiteDatabase sqLiteDatabase = null;
        ArrayList<ThemeData.Item> albumArraylist = new ArrayList<>();
        Cursor cursor = null;
        try {
            sqLiteDatabase = this.getWritableDatabase();
            cursor = sqLiteDatabase.rawQuery("select * from ALBUM" + user.getUserId() + "TBL", null);
            while (cursor.moveToNext()) {
                albumArraylist.add(data.new Item(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7)));
            }
            Log.d("DBSelectAlbumTBL", "성공");
        } catch (Exception e) {
            Log.d("DBSelectAlbumTBL", "성공");
        } finally {
            if (cursor != null) cursor.close();
            if (sqLiteDatabase != null) sqLiteDatabase.close();
        }
        return albumArraylist;
    }

    public void deleteAlbumData(User user, ThemeData.Item themeData) {
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.execSQL("DELETE FROM ALBUM" + user.getUserId() + "TBL WHERE datanum='" + themeData.getKey() + "';");
            Log.d("DB_DeleteAlbumData", "성공");
        } catch (SQLException e) {
            Log.d("DB_DeleteAlbumData", e.getMessage());
        } finally {
            if (sqLiteDatabase != null) sqLiteDatabase.close();
        }
    }
}
