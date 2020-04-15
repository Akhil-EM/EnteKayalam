package com.example.entenadu2;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.SliderViewAdapter;

import androidx.annotation.NonNull;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SliderAdapterExample extends
        SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;
    private int mCount;
    String [] imageUrls=new String[10];
    String imageUrl;
    public static final String TAG=SliderViewAdapter.class.getSimpleName();

    public SliderAdapterExample(Context context) {
        this.context = context;
    }

    public void setCount(int count) {
        this.mCount = count;
    }
    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(final SliderAdapterVH viewHolder, final int position) {
        final  StorageReference ref;
       final StorageReference storage;

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });


        switch (position) {
            case 0:
                ///viewHolder.imageGifContainer.setVisibility(View.GONE);
//                Glide.with(viewHolder.itemView)
//                        .load("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
//                        //.load("vmmmm")
//                        .fitCenter()
//                        .into(viewHolder.imageViewBackground);
//                storage = FirebaseStorage.getInstance().getReference();
//                ref = storage.child("images").child("imageNo0");
//                ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                @Override
//                public void onComplete(@NonNull Task<Uri> task) {
//                    Glide.with(viewHolder.itemView)
//                        .load(task.getResult().toString())
//                        .fitCenter()
//                        .into(viewHolder.imageViewBackground);
//                }
//            })
//            .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                new ShowMsg(context,"something went wrong");
//                            }
//                        });
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.image2)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);


                break;
            case 1:
//                storage = FirebaseStorage.getInstance().getReference();
//                ref = storage.child("images").child("imageNo1");
//                ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> task) {
//                        Glide.with(viewHolder.itemView)
//                                .load(task.getResult().toString())
//                                .fitCenter()
//                                .into(viewHolder.imageViewBackground);
//                               }
//                             })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                new ShowMsg(context,"something went wrong");
//                            }
//                        });
//
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.image1)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);

                break;
            case 2:
//                storage = FirebaseStorage.getInstance().getReference();
//                ref = storage.child("images").child("imageNo3");
//                ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> task) {
//                        Glide.with(viewHolder.itemView)
//                                .load(task.getResult().toString())
//                                .fitCenter()
//                                .into(viewHolder.imageViewBackground);
//                    }
//                })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                new ShowMsg(context,"something went wrong");
//                            }
//                        });
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.image3)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 4:
//                storage = FirebaseStorage.getInstance().getReference();
//                ref = storage.child("images").child("imageNo4");
//                ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> task) {
//                        Glide.with(viewHolder.itemView)
//                                .load(task.getResult().toString())
//                                .fitCenter()
//                                .into(viewHolder.imageViewBackground);
//                    }
//                })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                new ShowMsg(context,"something went wrong");
//                            }
//                        });
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.image4)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            default:
//                storage = FirebaseStorage.getInstance().getReference();
//                ref = storage.child("images").child("imageNo3");
//                ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> task) {
//                        Glide.with(viewHolder.itemView)
//                                .load(task.getResult().toString())
//                                .fitCenter()
//                                .into(viewHolder.imageViewBackground);
//                    }
//                })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                new ShowMsg(context,"something went wrong");
//                            }
//                        });
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.image5)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);

                break;

        }

    }


    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        //TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;
        }
    }


}