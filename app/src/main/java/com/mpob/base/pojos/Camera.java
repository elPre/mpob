package com.mpob.base.pojos;

import android.graphics.drawable.Drawable;

/**
 * Created by HOLV on 1,January,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public class Camera {

    private String cameraUrl;
    private String cameraName;
    private String cameraDescription;
    private Drawable pictureCamera;

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

    public static class Builder {
        private String cameraUrl;
        private String cameraName;
        private String cameraDescription;
        private Drawable pictureCamera;

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

        public Camera build() {
            return new Camera(this);
        }

    }

    private Camera(Builder builder) {
        this.pictureCamera = builder.pictureCamera;
        this.cameraName = builder.cameraName;
        this.cameraDescription = builder.cameraDescription;
        this.cameraUrl = builder.cameraUrl;
    }


}
