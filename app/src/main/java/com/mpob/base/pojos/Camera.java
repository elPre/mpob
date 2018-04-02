package com.mpob.base.pojos;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HOLV on 1,January,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public class Camera implements Parcelable{

    private String cameraUrl;
    private String cameraName;
    private String cameraDescription;
    private Drawable pictureCamera;
    private String extension;
    private String thumbNailUrl;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bitmap bitmap = ((BitmapDrawable) pictureCamera).getBitmap();

        dest.writeString(cameraUrl);
        dest.writeString(cameraName);
        dest.writeString(cameraDescription);
        dest.writeParcelable(bitmap, flags);
        dest.writeString(extension);
        dest.writeString(thumbNailUrl);

    }


    private Camera(Parcel in) {

        cameraUrl = in.readString();
        cameraName = in.readString();
        cameraDescription = in.readString();
        Bitmap bitmap = (Bitmap)in.readParcelable(getClass().getClassLoader());
        pictureCamera = new BitmapDrawable(bitmap);
        extension = in.readString();
        thumbNailUrl = in.readString();


    }


    public static final Creator<Camera> CREATOR
            = new Creator<Camera>() {
        public Camera createFromParcel(Parcel in) {
            return new Camera(in);
        }

        public Camera[] newArray(int size) {
            return new Camera[size];
        }
    };



    public Drawable getPictureCamera() {
        return pictureCamera;
    }

    public void setPictureCamera(Drawable pictureCamera) {
        this.pictureCamera = pictureCamera;
    }

    public String getCameraUrl() {
        return cameraUrl;
    }

    public void setCameraUrl(String cameraUrl) {
        this.cameraUrl = cameraUrl;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getCameraDescription() {
        return cameraDescription;
    }

    public void setCameraDescription(String cameraDescription) {
        this.cameraDescription = cameraDescription;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }


    public String getThumbNailUrl() {
        return thumbNailUrl;
    }

    public void setThumbNailUrl(String thumbNailUrl) {
        this.thumbNailUrl = thumbNailUrl;
    }

    public static class Builder {
        private String cameraUrl;
        private String cameraName;
        private String cameraDescription;
        private Drawable pictureCamera;
        private String extension;
        private String thumbNailUrl;

        public Builder url(String url){
            this.cameraUrl = url;
            return this;
        }

        public Builder name(String cameraName) {
            this.cameraName = cameraName;
            return this;
        }

        public Builder description(String cameraDescription) {
            this.cameraDescription = cameraDescription;
            return this;
        }

        public Builder picture(Drawable pictureCamera) {
            this.pictureCamera = pictureCamera;
            return this;
        }

        public Builder extension(String extension) {
            this.extension = extension;
            return this;
        }

        public Builder thumbnail(String thumbNailUrl){
            this.thumbNailUrl = thumbNailUrl;
            return this;
        }

        public Camera build() {
            return new Camera(this);
        }

    }

    private Camera(Builder builder) {
        this.pictureCamera = builder.pictureCamera;
        this.cameraName = builder.cameraName;
        this.cameraDescription = builder.cameraDescription;
        this.cameraUrl = builder.cameraUrl;
        this.extension = builder.extension;
        this.thumbNailUrl = builder.thumbNailUrl;
    }


}
