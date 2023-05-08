package com.example.sueobmwodeudji.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.sueobmwodeudji.databinding.ItemHomeSub1Binding;

public class HomeSubTimeTable extends View  {
    private ItemHomeSub1Binding binding;

    public HomeSubTimeTable(Context context) {
        super(context);
    }

    public HomeSubTimeTable(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(100f);

        canvas.drawText("안녕하세요", 0f, 100f, paint);
        onDrawCircle(canvas);

    }

    protected void onDrawCircle(Canvas canvas) {
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.BLUE);

        canvas.drawCircle(150f, 300f, 100f, paint2);
    }
}
