package edu.sjsu.android.newrecyclebuddy;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class InfoTopic implements Parcelable {
    private final int name, description, image;

    public InfoTopic(int name, int description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    protected InfoTopic(Parcel in) {
        name = in.readInt();
        description = in.readInt();
        image = in.readInt();
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
    public int getDescription() {
        return description;
    }
    public int getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(name);
        dest.writeInt(description);
        dest.writeInt(image);
    }
}
