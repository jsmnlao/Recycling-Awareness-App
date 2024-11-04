package edu.sjsu.android.recyclebuddy;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class InfoTopic implements Parcelable {
    private final int name, description;

    public InfoTopic(int name, int description) {
        this.name = name;
        this.description = description;
    }

    protected InfoTopic(Parcel in) {
        name = in.readInt();
        description = in.readInt();
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

    public int getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(name);
        dest.writeInt(description);
    }
}
