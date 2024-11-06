package edu.sjsu.android.recyclebuddy;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Topic implements Parcelable {

    private final String name;

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    protected Topic(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel in) {
            return new Topic(in);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
    }
}
