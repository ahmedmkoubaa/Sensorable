package com.commons.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

<<<<<<< HEAD
import java.util.Objects;

=======
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f

@Entity
public class ActivityEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "title")
    public String title;

    @NonNull
    @ColumnInfo(name = "description")
    public String description;

<<<<<<< HEAD
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEntity ActivityEntity = (ActivityEntity) o;
        return id == ActivityEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }

=======
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
    public ActivityEntity(@NonNull int id, @NonNull String title, @NonNull String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}