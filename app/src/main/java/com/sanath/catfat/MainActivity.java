package com.sanath.catfat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {

    public static final String FIRST_NAME = "first" ;
    public static final String LAST_NAME = "last" ;

    private RecyclerView recyclerView;
    private ExampleAdapter exampleAdapter;
    public static ArrayList<ExampleItem> mExampleList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        parseJson();
    }


    public String firstName;
    public String lastName;

    private void parseJson() {
        String url = "https://cat-fact.herokuapp.com/facts";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("all");
                            for (int i = 0; i <jsonArray.length() ; i++) {

                                JSONObject alls = jsonArray.getJSONObject(i);

                                String type = alls.getString("type");

                                if (alls.has("user")){
                                    JSONObject user = alls.getJSONObject("user");
                                    if (user.has("name")){
                                        JSONObject name = user.getJSONObject("name");
                                        if (name.has("first")){
                                            System.out.println("First Name : "+name.getString("first"));
                                            System.out.println("Last Name : "+name.getString("last"));
                                            firstName = name.getString("first");
                                            lastName = name.getString("last");
                                        }
                                    }
                                }

                                String text = alls.getString("text");
                                String upVotes = alls.getString("upvotes");

                                mExampleList.add(new ExampleItem(text, type, upVotes, firstName, lastName));
                            }

                            exampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);
                            recyclerView.setAdapter(exampleAdapter);
                            exampleAdapter.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }


    @Override
    public void onItemClick(int position) {
        Intent userIntent = new Intent(this, DetailsActivity.class);
        ExampleItem clickedItem = mExampleList.get(position);

        userIntent.putExtra(FIRST_NAME, clickedItem.getFirstName());
        userIntent.putExtra(LAST_NAME, clickedItem.getLastName());

        startActivity(userIntent);
    }
}
