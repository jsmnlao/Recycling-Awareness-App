package edu.sjsu.android.project3jasminelao;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Coffee implements Parcelable {
    private final int imageID;
    private final int name, description;

    // alt + insert for faster setup

    public Coffee(int imageID, int name, int description) {
        this.imageID = imageID;
        this.name = name;
        this.description = description;
    }

    protected Coffee(Parcel in) {
        imageID = in.readInt();
        name = in.readInt();
        description = in.readInt();
    }

    public static final Creator<Coffee> CREATOR = new Creator<Coffee>() {
        @Override
        public Coffee createFromParcel(Parcel in) {
            return new Coffee(in);
        }

        @Override
        public Coffee[] newArray(int size) {
            return new Coffee[size];
        }
    };

    public int getImageID() {
        return imageID;
    }

    public int getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(imageID);
        dest.writeInt(name);
        dest.writeInt(description);
    }
}
