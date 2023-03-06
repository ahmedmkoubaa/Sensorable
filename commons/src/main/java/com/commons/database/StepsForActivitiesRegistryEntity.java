package com.commons.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = ActivityEntity.class,
                parentColumns = "id",
                childColumns = "id_activity",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = ActivityStepEntity.class,
                parentColumns = "id",
                childColumns = "id_step",
                onDelete = ForeignKey.CASCADE)
<<<<<<< HEAD
}, tableName = "StepsForActivitiesRegistryEntity")
=======
})
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
public class StepsForActivitiesRegistryEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "id_activity")
    public long idActivity;

    @NonNull
    @ColumnInfo(name = "id_step")
    public int idStep;

<<<<<<< HEAD
=======

>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
    @NonNull
    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "user_id")
    public String userId;

<<<<<<< HEAD
    @ColumnInfo(name = "clicked")
    public boolean clicked = false;

    public StepsForActivitiesRegistryEntity(@NonNull long idActivity, int idStep, @NonNull long timestamp, @NonNull String userId, boolean clicked) {
=======
    public StepsForActivitiesRegistryEntity(@NonNull long idActivity, int idStep, @NonNull long timestamp, @NonNull String userId) {
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
        this.idActivity = idActivity;
        this.idStep = idStep;
        this.timestamp = timestamp;
        this.userId = userId;
<<<<<<< HEAD
        this.clicked = clicked;
=======
>>>>>>> 810a0007043752cf8e90356a8aeac68494f1e25f
    }

    public String toJson() {
        return "[" + idActivity + "," + idStep + "," + timestamp + ", \"" + userId + "\" ]";
    }
}