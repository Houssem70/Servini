package com.example.servini;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

public class CircularMarkerUtils {

    public static void loadCircularMarker(Picasso picasso, String imageUrl, GoogleMap googleMap, LatLng position, String emlpoyeeName) {
        MarkerOptions markerOptions = new MarkerOptions().position(position).title(emlpoyeeName);

        picasso.load(imageUrl)
                .transform(new CircleTransformation(75))
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                        googleMap.addMarker(markerOptions);
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 12f));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        // Handle bitmap loading failure
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        // Handle bitmap loading preparation
                    }
                });
    }

    public static class CircleTransformation implements Transformation {
        private int targetSize; // Desired size of the circular marker

        public CircleTransformation(int targetSize) {
            this.targetSize = targetSize;
        }
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            float scale = (float) targetSize / size;

            Bitmap scaledBitmap = Bitmap.createScaledBitmap(source, (int) (source.getWidth() * scale), (int) (source.getHeight() * scale), true);
            if (scaledBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(targetSize, targetSize, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float radius = targetSize / 2f;
            canvas.drawCircle(radius, radius, radius, paint);

            scaledBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
}
