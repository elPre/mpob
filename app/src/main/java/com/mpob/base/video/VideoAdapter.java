package com.mpob.base.video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mpob.base.R;
import com.mpob.base.pojos.Camera;

import java.util.List;

/**
 * Created by HOLV on 1,January,2018
 * My Parents On Board,
 * Santa Monica California.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    private static final String TAG = VideoAdapter.class.getSimpleName();

    private Context context = null;
    private List<Camera> list = null;
    private IVideoAPI.CallBack callBack = null;

    public VideoAdapter(Context context, List<Camera> list){
        this.context = context;
        this.list = list;
    }

    public void setCallBack(VideoModel model) {
        callBack = model;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(context).inflate(R.layout.video_recyclerview_view_vh, parent, false));
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        holder.bindCamera(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    protected class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView cameraThumbnail = null;
        private TextView cameraName = null;
        private TextView cameraDescription = null;

        public VideoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cameraThumbnail = (ImageView) itemView.findViewById(R.id.video_rv_image_view);
            cameraName = (TextView) itemView.findViewById(R.id.video_rv_camera_name);
            cameraDescription = (TextView) itemView.findViewById(R.id.video_rv_camera_description);
        }

        public void bindCamera(Camera camera) {
            cameraThumbnail.setImageDrawable(camera.getPictureCamera());
            cameraName.setText(camera.getCameraName());
            cameraDescription.setText(camera.getCameraDescription());
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "getting the adapter position: " + getAdapterPosition());
            callBack.selectCameraToPlay(list.get(getAdapterPosition()));
        }
    }


}
