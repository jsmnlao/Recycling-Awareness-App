package edu.sjsu.android.recyclebuddy;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class InfoTopic implements Parcelable {
    private final String name;

    public InfoTopic(String name) {
        this.name = name;
    }

    protected InfoTopic(Parcel in) {
        name = in.readString();
    }

    public static final Creator<InfoTopic> CREATOR = new Creator<InfoTopic>() {
        @Override
        public InfoTopic createFromParcel(Parcel in) {
            return new InfoTopic(in);
        }

        @Override
        public InfoTopic[] newArray(int size) {
            return new InfoTopic[size];
        }
    };

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
    }
}
