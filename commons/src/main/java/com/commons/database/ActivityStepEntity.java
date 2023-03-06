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
public class ActivityStepEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "title")
    public String title;

    public ActivityStepEntity(@NonNull int id, @NonNull String title) {
        this.id = id;
        this.title = title;
    }

<<<<<<< HEAD
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityStepEntity ActivityStepEntity = (ActivityStepEntity) o;
        return id == ActivityStepEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

=======
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
    public int getId() {
        return id;
    }

<<<<<<< HEAD

=======
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
    public String getTitle() {
        return title;
    }


}