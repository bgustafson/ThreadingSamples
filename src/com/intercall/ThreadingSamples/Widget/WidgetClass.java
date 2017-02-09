package com.intercall.ThreadingSamples.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.intercall.ThreadingSamples.MainActivity;
import com.intercall.ThreadingSamples.R;

import java.util.Random;

public class WidgetClass extends AppWidgetProvider {

    private static Random rand = new Random();
    private String[] strings = {"AAA", "BBB", "CCC", "DDD", "EEE"};

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        int widgetCount = appWidgetIds.length;

        for(int i = 0; i < widgetCount; i++) {

            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch MainActivity
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Get the layout for the App Widget and attach an on-click listener to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setOnClickPendingIntent(R.id.open_app, pendingIntent);

            int random = rand.nextInt(5);
            views.setTextViewText(R.id.updateText, strings[random]);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}