package rohan.com.livecricketscores1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    Context context;
    private List<LiveScoreData> mScoreDatas;
    String jsondata;
    private String mLiveResults;
    private List<LiveScoreData> feedItemList = Collections.emptyList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        InitialiseData();



    }
    private void InitialiseData()
    {


        if (isNetworkAvailable()) {
            String liveUrl = "http://cricinfo-mukki.rhcloud.com/api/match/live/";
            //     toggleRefresh();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(liveUrl).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            toggleRefresh();
//                        }
//                    });
//                    alertUserAboutError();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //toggleRefresh();
                            }
                        });
                        //Response response =call.execute();
                        jsondata = response.body().string();
                        Log.d("Rohan", jsondata);
                        if (response.isSuccessful()) {
                            parseResult(jsondata);
//                            mLiveResults = parseLiveResulta(jsondata);
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    updateDisplay();
//                                }
//                            });


                            Log.v("Rohan", jsondata);
                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e("Rohan", "EXception Caught", e);
                    }
                }

                private void alertUserAboutError() {
                //    AlertDialogFragment dialog = new AlertDialogFragment();
                 //   dialog.show(getFragmentManager(), "error_dialog");

                }
            });
            Log.d("Rohan", "Main UI code is running");
        } else

        {
            Toast.makeText(this, "Network Unavailable", Toast.LENGTH_LONG).show();
        }
//        mScoreDatas = new ArrayList<>();
//        mScoreDatas.add(new LiveScoreData("India","Australia","200"));
//        mScoreDatas.add(new LiveScoreData("Pakisan","Australia","300"));
//        mScoreDatas.add(new LiveScoreData("S Africa","India","400"));
//        mScoreDatas.add(new LiveScoreData("India","New Zealand","300"));


    }
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE );
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo !=null && networkInfo.isConnected() )
        {
            isAvailable = true;
        }
        return isAvailable;
    }
    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);

            JSONArray posts = response.getJSONArray("items");
            // feedsList = new ArrayList<>();
            if (feedItemList.isEmpty()) {
                feedItemList = new ArrayList<LiveScoreData>();
            }

                for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                LiveScoreData item = new LiveScoreData();
                item.setTeam1Name(post.optString("title"));
                    item.setTeam2Name(post.optString("matchId"));
                Log.d("Rohan",item.getTeam1Name());
                //       item.setThumbnail(post.optString("matchId"));

                feedItemList.add(item);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    RVAdapter adapter = new RVAdapter(feedItemList);
                    rv.setAdapter(adapter);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
