package ir.batna.monadi;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {

    EditText tokenEditText;
    EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tokenEditText = findViewById(R.id.tokent_box);
        messageEditText = findViewById(R.id.message_box);

    }

    public void pushMessage(View view) {

        String token = tokenEditText.getText().toString();
        String message = messageEditText.getText().toString();


        final String messsageString = "{" +
                "\"type\":\"sendpush\"," +
                "\"token\":\"" + token +
                "\",\"data\":\"" + message + "\"" +
                "}";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://push.batna.ir:8010").build();
        WebSocketListener listener = new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                webSocket.send(messsageString);
                Log.v("response: ", String.valueOf(response.message()));
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);


            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
                Log.i("Main Activity WebSocket", "");
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                Log.i("Main Activity WebSocket", "");
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
//                super.onFailure(webSocket, t, response);
                Log.i("Main Activity WebSocket", "");
            }
        };
        WebSocket ws = client.newWebSocket(request, listener);
    }
}

