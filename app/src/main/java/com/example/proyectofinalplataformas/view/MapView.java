package com.example.proyectofinalplataformas.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
public class MapView extends View{

    private Paint paint;
    private Paint paintText;

    public MapView(Context context) {
        super(context);
        init();
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setTextSize(40);

        paintText = new Paint();
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(40);
        paintText.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // SALA
        canvas.drawLine(300, 100, 700, 100, paint); // Linea superior
        canvas.drawLine(700, 100, 700, 200, paint); // Linea derecha
        canvas.drawLine(300, 200, 500, 200, paint); // Linea inferior 1
        canvas.drawLine(550, 200, 700, 200, paint); // Linea inferior 2
        canvas.drawText("SALA", 450, 170, paintText);

        // GALERIA VI
        canvas.drawLine(100, 100, 300, 100, paint); // Linea superior
        canvas.drawLine(100, 100, 100, 600, paint); // Linea izquierda
        canvas.drawLine(300, 100, 300, 125, paint); // Linea derecha 1
        canvas.drawLine(300, 175, 300, 450, paint); // Linea derecha 2
        canvas.drawLine(100, 500, 300, 500, paint); // Linea inferior
        canvas.drawText("GALERIA", 120, 300, paintText);
        canvas.drawText("VI", 180, 350, paintText);


        // GALERIA II
        canvas.drawLine(100, 600, 300, 600, paint); // Linea superior
        canvas.drawLine(100, 600, 100, 1000, paint); // Linea izquierda
        canvas.drawLine(300, 700, 300, 800, paint); // Linea derecha 1
        canvas.drawLine(300, 850, 300, 1000, paint); // Linea derecha 2
        canvas.drawLine(100, 700, 175, 700, paint); // Linea inferior 1
        canvas.drawLine(225, 700, 300, 700, paint); // Linea inferior 2
        canvas.drawLine(100, 1000, 175, 1000, paint); // Linea inferior 3
        canvas.drawLine(225, 1000, 300, 1000, paint); // Linea inferior 4
        canvas.drawText("GALERIA", 120, 850, paintText);
        canvas.drawText("II", 195, 900, paintText);
        canvas.drawText("G - II", 160, 660, paintText);

        // GALERIA III
        canvas.drawLine(300, 600, 450, 600, paint); // Linea superior 1
        canvas.drawLine(500, 600, 700, 600, paint); // Linea superior 2
        canvas.drawLine(300, 600, 300, 625, paint); // Linea izquierda 1
        canvas.drawLine(300, 675, 300, 700, paint); // Linea izquierda 2
        canvas.drawLine(700, 600, 700, 700, paint); // Linea derecha
        canvas.drawLine(300, 700, 400, 700, paint); // Linea inferior 1
        canvas.drawLine(450, 700, 700, 700, paint); // Linea inferior 2
        canvas.drawText("GALERIA III", 410, 660, paintText);

        // GALERIA I
        canvas.drawLine(300, 1050, 600, 1050, paint); // Linea superior 2
        canvas.drawLine(650, 1050, 800, 1050, paint); // Linea superior 2
        canvas.drawLine(100, 1000, 100, 1200, paint); // Linea izquierda
        canvas.drawLine(300, 1100, 300, 1200, paint); // Separacion 1
        canvas.drawLine(300, 1000, 300, 1050, paint); // Separacion 2
        canvas.drawLine(800, 1050, 800, 1200, paint); // Linea derecha
        canvas.drawLine(100, 1200, 800, 1200, paint); // Linea inferior
        canvas.drawText("GALERIA I", 460, 1140, paintText);
        canvas.drawText("G - I", 170, 1100, paintText);

        // GALERIA V
        canvas.drawLine(850, 600, 1010, 600, paint); // Linea superior
        canvas.drawLine(850, 600, 850, 700, paint); // Linea izquierda 1
        canvas.drawLine(850, 750, 850, 850, paint); // Linea izquierda 2
        canvas.drawLine(1010, 600, 1010, 850, paint); // Linea derecha
        canvas.drawLine(850, 850, 1010, 850, paint); // Linea inferior
        canvas.drawText("GALERIA", 855, 700, paintText);
        canvas.drawText("V", 915, 750, paintText);

        // GALERIA IV
        canvas.drawLine(850, 850, 850, 950, paint); // Linea izquierda 1
        canvas.drawLine(850, 1000, 850, 1200, paint); // Linea izquierda 2
        canvas.drawLine(1010, 850, 1010, 1200, paint); // Linea derecha
        canvas.drawLine(850, 1100, 905, 1100, paint); // Linea inferior 1
        canvas.drawLine(955, 1100, 1010, 1100, paint); // Linea inferior 2
        canvas.drawLine(850, 1200, 1010, 1200, paint); // Linea inferior 3
        canvas.drawText("GALERIA", 855, 950, paintText);
        canvas.drawText("VI", 915, 1000, paintText);
        canvas.drawText("G - VI", 890, 1160, paintText);

    }

}
