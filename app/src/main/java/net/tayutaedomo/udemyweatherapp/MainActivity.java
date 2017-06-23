package net.tayutaedomo.udemyweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    TextView mTitle;
    TextView mDescription;
    TextView mDateLabel0, mDateLabel1;
    TextView mTelop0, mTelop1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = (TextView)findViewById(R.id.title);
        mDescription = (TextView)findViewById(R.id.description);
        mDateLabel0 = (TextView)findViewById(R.id.dateLabel0);
        mDateLabel1 = (TextView)findViewById(R.id.dateLabel1);
        mTelop0 = (TextView)findViewById(R.id.telop0);
        mTelop1 = (TextView)findViewById(R.id.telop1);

        String url = "http://weather.livedoor.com/forecast/webservice/json/v1?city=140010";

        JsonObjectRequest jsObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d(TAG, response.toString(2));

                            mTitle.setText(response.getString("title"));
                            mDescription.setText(response.getJSONObject("description").getString("text"));
                            mDateLabel0.setText(response.getJSONArray("forecasts").getJSONObject(0).getString("dateLabel"));
                            mDateLabel1.setText(response.getJSONArray("forecasts").getJSONObject(1).getString("dateLabel"));
                            mTelop0.setText(response.getJSONArray("forecasts").getJSONObject(0).getString("telop"));
                            mTelop1.setText(response.getJSONArray("forecasts").getJSONObject(1).getString("telop"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                    }
                }
        );

        MySingleton.getInstance(this).addToRequestQueue(jsObjectRequest);
    }
}
