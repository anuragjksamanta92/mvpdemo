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
import com.basu.data.network.model.State;
import com.basu.utils.VolleyClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateNameFilterable extends AppCompatActivity {

    private TextView edt_search;
    private RecyclerView recyclerView;


    private List<State> stateList;
    private VolleyClass volleyClass;
    private String jwtToken;
    private String countryId;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_name_filterable);

        jwtToken = getIntent().getExtras().getString("jwToken");
        countryId = getIntent().getExtras().getString("id");

        volleyClass = new VolleyClass(this);

        edt_search = findViewById(R.id.edt_search);
        recyclerView = findViewById(R.id.recyclerView);
        getStateList();

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


    private void getStateList() {
        JSONObject urlObject = new JSONObject();
        try {
            urlObject.put("country_id", countryId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, ApiEndPoint.ENDPOINT_STATES_CODE_RESPONSE, urlObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                stateList = new ArrayList<>();
                Log.e("Update Response: ", response.toString());
                try {
                    if (response.getBoolean("status")) {
                        JSONArray jsonArray = response.getJSONArray("response");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            State state = new State();
                            state.setName(jsonObject.getString("name"));
                            stateList.add(state);
                        }
                        mAdapter = new RecyclerViewAdapter(stateList);
                        recyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(StateNameFilterable.this, response.getString("status_msg"), Toast.LENGTH_LONG).show();
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
        private List<State> stateList;
        private List<State> stateListFiltered;

        RecyclerViewAdapter(List<State> stateList) {
            this.stateListFiltered = stateList;
            this.stateList = stateList;
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_state_name;

            ViewHolder(View itemView) {
                super(itemView);
                tv_state_name = itemView.findViewById(R.id.tv_state_name);
                itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(StateNameFilterable.this, RegistrationActivity.class);
                    intent.putExtra("name", tv_state_name.getText());
                    setResult(RESULT_OK, intent);
                    finish();
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
                        stateListFiltered = stateList;
                    } else {
                        List<State> filteredList = new ArrayList<>();
                        for (State row : stateList) {
                            if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }
                        stateListFiltered = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = stateListFiltered;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                    stateListFiltered = (ArrayList<State>) filterResults.values;
                    notifyDataSetChanged();
                }
            };
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_name_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tv_state_name.setText(stateListFiltered.get(position).getName());
        }


        @Override
        public int getItemCount() {
            return stateListFiltered.size();
        }
    }
}
