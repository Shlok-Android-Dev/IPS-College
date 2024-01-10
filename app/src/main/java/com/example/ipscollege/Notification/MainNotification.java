package com.example.ipscollege.Notification;

import static com.example.ipscollege.Notification.Constants.TOPIC;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ipscollege.Notification.Api.ApiUtilities;
import com.example.ipscollege.Notification.model.NotificationData;
import com.example.ipscollege.Notification.model.PushNotification;
import com.example.ipscollege.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainNotification extends AppCompatActivity {


    private TextInputEditText title, message;
    private Button pushNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notification);


        title =findViewById(R.id.notificationTitle);
        message = findViewById(R.id.notificationMessage);
        pushNotification = findViewById(R.id.pushNotification);

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);

        pushNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = title.getText().toString();
                String messageText = message.getText().toString();

                if (!titleText.isEmpty() && !messageText.isEmpty()){
                    PushNotification notification =new  PushNotification(new NotificationData(titleText, messageText), TOPIC);
                    sendNotification(notification);
                }
            }
        });

    }

    private void sendNotification(PushNotification notification) {
        ApiUtilities.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
            @Override
            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {

                if (response.isSuccessful()){

                    Toast.makeText(MainNotification.this, "Notification Send SuccessFully", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainNotification.this, "Failed", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<PushNotification> call, Throwable t) {
                Toast.makeText(MainNotification.this, "Error:"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}