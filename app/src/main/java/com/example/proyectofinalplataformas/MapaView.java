package com.example.proyectofinalplataformas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
public class MapaView extends View{
    private Bitmap mapaPrincipal;
    private Bitmap[] galerias;
    private Bitmap imagenActual;
    private Paint paint;

    public MapaView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        mapaPrincipal = BitmapFactory.decodeResource(getResources(), R.drawable.mapa_centro_cultural);
        galerias = new Bitmap[]{
                BitmapFactory.decodeResource(getResources(), R.drawable.galeria1),
                BitmapFactory.decodeResource(getResources(), R.drawable.galeria2),
                BitmapFactory.decodeResource(getResources(), R.drawable.galeria3),
                BitmapFactory.decodeResource(getResources(), R.drawable.galeria4),
                BitmapFactory.decodeResource(getResources(), R.drawable.galeria5),
                BitmapFactory.decodeResource(getResources(), R.drawable.galeria6),
                BitmapFactory.decodeResource(getResources(), R.drawable.sala),
        };
        imagenActual = mapaPrincipal;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(imagenActual, 0, 0, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            // verifica coordenadas para el area
            if (dentroDeArea(x, y, 1)) {
                imagenActual = galerias[0];
            } else if (dentroDeArea(x, y, 2)) {
                imagenActual = galerias[1];
            } else if (dentroDeArea(x, y, 3)) {
                imagenActual = galerias[2];
            } else if (dentroDeArea(x, y, 4)) {
                imagenActual = galerias[3];
            } else if (dentroDeArea(x, y, 5)) {
                imagenActual = galerias[4];
            } else if (dentroDeArea(x, y, 6)) {
                imagenActual = galerias[5];
            } else if (dentroDeArea(x, y, 7)) {
                imagenActual = galerias[6];
            } else {
                imagenActual = mapaPrincipal;
            }
            invalidate();
        }
        return super.onTouchEvent(event);
    }

    private boolean dentroDeArea(float x, float y, int area) {
        switch (area) {
            case 1:
                return x >= 74 && x <= 1635 && y >= 2007 && y <= 2340;
            case 2:
                return x >= 74 && x <= 610 && y >= 1065 && y <= 2006;
            case 3:
                return x >= 611 && x <= 1490 && y >= 1066 && y <= 1365;
            case 4:
                return x >= 1844 && x <= 2210 && y >= 1610 && y <= 2340;
            case 5:
                return x >= 1844 && x <= 2210 && y >= 1050 && y <= 1609;
            case 6:
                return x >= 74 && x <= 550 && y >= 66 && y <= 910;
            case 7:
                return x >= 551 && x <= 1491 && y >= 65 && y <= 335;
            default:
                return false;
        }
    }
}
