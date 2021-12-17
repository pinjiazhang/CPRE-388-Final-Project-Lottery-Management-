package com.example.latest_lottery.Processor;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.latest_lottery.R;
import com.google.gson.JsonArray;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnLoadImageListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Latest Fragment Handler for the data processing and binding for the main activity
 * By using the recyclerview and bind to the main activity we can easily make changes to the data since lottery data is likely to change if user
 * work on some operations. Recyclerview is more flexible compare to the normal textview for data binding and change.
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class Latest_Process extends RecyclerView.Adapter {
    public static final int item_top=1;
    public static final int item_middle=2;
    public static final int item_bottom=3;


    private final Context context;
    private final JSONArray response;
    private final LayoutInflater inflater;
    public int current=1;

    public Latest_Process(Context context, JSONArray response) {

        this.context=context;
        this.response=response;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType==item_top) {
            return new Item_top_view(context, inflater.inflate(R.layout.item_top, null));
        }
        else if(viewType==item_middle){
            return new Item_middle_view(context, inflater.inflate(R.layout.item_middle, null));
        }
        else

            return null;

    }

    class Item_top_view extends RecyclerView.ViewHolder{
        private Context context;
        private Banner banner;
        public Item_top_view(Context context, View inflate) {
            super(inflate);
            this.context=context;
            this.banner=inflate.findViewById(R.id.item_top);
        }
        public void finalize(JSONArray j){
            banner.isAutoPlay(false);
            List<String> imageurl=new ArrayList<>();
            imageurl.add("https://apimobile1z09p6j.epyin.net/dlt.php");//Injecting the data from the server and pull it to the client port.
            imageurl.add("https://apimobile1z09p6j.epyin.net/ssq.php");
            imageurl.add("https://apimobile1z09p6j.epyin.net/qlc.php");
            imageurl.add("https://apimobile1z09p6j.epyin.net/pl3.php");
            imageurl.add("https://apimobile1z09p6j.epyin.net/pl5.php");
            //banner.setBannerAnimation(Transformer.Accordion);
            banner.setBannerAnimation(Transformer.DepthPage);
            banner.setImages(imageurl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    Glide.with(context).load(url).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(view);
                }//Using banner tool to implement the scroll animation
            });





        }
    }
    class Item_middle_view extends RecyclerView.ViewHolder{
            private Context context;
            private GridView item_grid;
            private Item_Process item_process;
        public Item_middle_view(Context context,@NonNull View itemView) {
            super(itemView);
            this.context=context;
            item_grid=itemView.findViewById(R.id.grid_view);
        }
        public void finalize(JSONArray j){
            item_process=new Item_Process(context,j);
            item_grid.setAdapter(item_process);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==1){
            Item_top_view top=(Item_top_view) holder;
            top.finalize(response);

        }
        else if(getItemViewType(position)==2){
            Item_middle_view middle=(Item_middle_view)holder;
            middle.finalize(response);
        }



    }


    @Override
    public int getItemViewType(int i) {

            if(i==0)
                current=1;
            else if(i==1)
                current=2;
            else
                current=3;
            return current;

    }

    @Override
    public int getItemCount() {

        return 2;//This can be added in the future for latest page if more contents need to be implemented. Right now the current view port is only 2
    }
}
