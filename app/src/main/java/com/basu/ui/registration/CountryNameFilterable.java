package com.basu.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.basu.R;
import com.basu.data.network.ApiEndPoint;
import com.basu.data.network.model.Country;
import com.basu.utils.VolleyClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryNameFilterable extends AppCompatActivity {

    private TextView edt_search;
    private RecyclerView recyclerView;


    private List<Country> countryList = new ArrayList<>();
    private VolleyClass volleyClass;
    private String jwtToken;
    private String className;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_name_filterable);

        jwtToken = getIntent().getExtras().getString("jwToken");

        volleyClass = new VolleyClass(this);

        edt_search = findViewById(R.id.edt_search);
        recyclerView = findViewById(R.id.recyclerView);
        getCountryList();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mAdapter.getFilter().filter(s);
            }
        });
    }


    private void getCountryList() {
        JSONObject urlObject = new JSONObject();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, ApiEndPoint.ENDPOINT_COUNTRY_CODE_RESPONSE, urlObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Update Response: ", response.toString());
                try {
                    if (response.getBoolean("status")) {
                        JSONArray jsonArray = response.getJSONArray("response");
                        countryList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Country country = new Country();
                            country.setId(jsonObject.getInt("id"));
                            country.setName(jsonObject.getString("name"));
                            country.setPhonecode(jsonObject.getString("phonecode"));
                            countryList.add(country);
                        }
                        mAdapter = new RecyclerViewAdapter(countryList);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(CountryNameFilterable.this, response.getString("status_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                error.printStackTrace();
            }
        }) {
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer: " + jwtToken);
                return headers;
            }
        };
        volleyClass.addToRequestQueue(jsObjRequest);
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
        private List<Country> countryList;
        private List<Country> countryListFiltered;

        RecyclerViewAdapter(List<Country> countryList) {
            this.countryListFiltered = countryList;
            this.countryList = countryList;
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_country_name;

            ViewHolder(View itemView) {
                super(itemView);
                tv_country_name = itemView.findViewById(R.id.tv_country_name);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = 0;
                        for (int i = 0; i < countryList.size(); i++) {
                            if (countryList.get(i).getName().equals(tv_country_name.getText().toString())) {
                                id = countryList.get(i).getId();
                                break;
                            }
                        }
                        Intent intent = new Intent(CountryNameFilterable.this, RegistrationActivity.class);
                        intent.putExtra("name", tv_country_name.getText());
                        intent.putExtra("id", id);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        countryListFiltered = countryList;
                    } else {
                        List<Country> filteredList = new ArrayList<>();
                        for (Country row : countryList) {
                            if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }
                        countryListFiltered = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = countryListFiltered;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                    countryListFiltered = (ArrayList<Country>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_name_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tv_country_name.setText(countryListFiltered.get(position).getName());
        }


        @Override
        public int getItemCount() {
            return countryListFiltered.size();
        }
    }
}
