package com.example.adminapp.data;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppealDao_Impl implements AppealDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Appeal> __insertionAdapterOfAppeal;

  private final EntityDeletionOrUpdateAdapter<Appeal> __deletionAdapterOfAppeal;

  private final EntityDeletionOrUpdateAdapter<Appeal> __updateAdapterOfAppeal;

  public AppealDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppeal = new EntityInsertionAdapter<Appeal>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `appeals` (`id`,`text`,`status`,`date`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Appeal value) {
        stmt.bindLong(1, value.getId());
        if (value.getText() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getText());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getStatus());
        }
        stmt.bindLong(4, value.getDate());
      }
    };
    this.__deletionAdapterOfAppeal = new EntityDeletionOrUpdateAdapter<Appeal>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `appeals` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Appeal value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfAppeal = new EntityDeletionOrUpdateAdapter<Appeal>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `appeals` SET `id` = ?,`text` = ?,`status` = ?,`date` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Appeal value) {
        stmt.bindLong(1, value.getId());
        if (value.getText() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getText());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getStatus());
        }
        stmt.bindLong(4, value.getDate());
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public Object insert(final Appeal appeal, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppeal.insert(appeal);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final Appeal appeal, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAppeal.handle(appeal);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object update(final Appeal appeal, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAppeal.handle(appeal);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<Appeal>> getAllAppeals() {
    final String _sql = "SELECT * FROM appeals ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"appeals"}, new Callable<List<Appeal>>() {
      @Override
      public List<Appeal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<Appeal> _result = new ArrayList<Appeal>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Appeal _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            _item = new Appeal(_tmpId,_tmpText,_tmpStatus,_tmpDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
