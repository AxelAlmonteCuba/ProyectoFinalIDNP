package com.example.proyectofinalplataformas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Path;
import android.view.MotionEvent;
import com.example.proyectofinalplataformas.Entitys.Picture;
import com.example.proyectofinalplataformas.Entitys.Room;
import com.example.proyectofinalplataformas.fragments.MapDescripcionFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.proyectofinalplataformas.R;

import java.util.List;

public class MapView extends View {
    private Paint polygonPaint;
    private Paint circlePaint;
    private Paint textPaint;
    private Path path;
    private List<Room> rooms;
    private List<Picture> pictures;
    private float scaleFactor = 1.0f;

    public MapView(Context context, List<Room> rooms, List<Picture> pictures) {
        super(context);
        this.rooms = rooms;
        this.pictures = pictures;
        init();
    }

    public MapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        polygonPaint = createPaint(Color.RED, Paint.Style.STROKE, 5);
        circlePaint = createPaint(Color.parseColor("#006400"), Paint.Style.FILL, 0);
        textPaint = createPaint(Color.WHITE, Paint.Style.FILL, 60);
        textPaint.setTextSize(40);
        textPaint.setAntiAlias(true);
        path = new Path();
    }

    private Paint createPaint(int color, Paint.Style style, float strokeWidth) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Calcular factor de escala dinámico
        calculateScaleFactor();

        // Dibujar polígonos
        for (Room room : rooms) {
            path.reset();
            float[][] coordinates = room.getCoordinates();
            path.moveTo(coordinates[0][0] * scaleFactor, coordinates[0][1] * scaleFactor);
            for (int j = 1; j < coordinates.length; j++) {
                path.lineTo(coordinates[j][0] * scaleFactor, coordinates[j][1] * scaleFactor);
            }
            path.close();
            canvas.drawPath(path, polygonPaint);

            // Calcular el centro de la habitación
            float[] center = calculatePolygonCenter(coordinates);
            drawCenteredText(canvas, room.getName(), center[0] * scaleFactor, center[1] * scaleFactor, textPaint);
        }

        // Dibujar pinturas
        for (Picture picture : pictures) {
            float[] coordinates = picture.getCoordinates();
            canvas.drawCircle(coordinates[0] * scaleFactor, coordinates[1] * scaleFactor, 30, circlePaint);
            canvas.drawText(picture.getLabel(), coordinates[0] * scaleFactor - 15,
                    coordinates[1] * scaleFactor + 15, textPaint);
        }
    }

    private void calculateScaleFactor() {
        float maxX = 0, maxY = 0;
        for (Room room : rooms) {
            for (float[] coord : room.getCoordinates()) {
                if (coord[0] > maxX) {
                    maxX = coord[0];
                }
                if (coord[1] > maxY) {
                    maxY = coord[1];
                }
            }
        }
        scaleFactor = Math.min(getWidth() / maxX, getHeight() / maxY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX() / scaleFactor;
            float y = event.getY() / scaleFactor;
            for (Picture picture : pictures) {
                float[] coordinates = picture.getCoordinates();
                float pictureX = coordinates[0];
                float pictureY = coordinates[1];
                float radius = 30 / scaleFactor; // Ajustar el radio del círculo según el factor de escala
                if (Math.pow(x - pictureX, 2) + Math.pow(y - pictureY, 2) <= Math.pow(radius, 2)) {
                    openCuadroFragment(picture.getLabel());
                    return true;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private void openCuadroFragment(String pictureLabel) {
        FragmentActivity activity = (FragmentActivity) getContext();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        MapDescripcionFragment cuadroFragment = MapDescripcionFragment.newInstance(pictureLabel);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, cuadroFragment)
                .addToBackStack(null)
                .commit();
    }

    private float[] calculatePolygonCenter(float[][] coordinates) {
        float xSum = 0, ySum = 0;
        int numPoints = coordinates.length;
        for (float[] coord : coordinates) {
            xSum += coord[0];
            ySum += coord[1];
        }
        return new float[]{xSum / numPoints, ySum / numPoints};
    }

    private void drawCenteredText(Canvas canvas, String text, float x, float y, Paint paint) {
        Paint.Align align = paint.getTextAlign();
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, x, y, paint);
        paint.setTextAlign(align);
    }
}