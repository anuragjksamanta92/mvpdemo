package com.basu.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.basu.CustomView.AutoCompleteEditTextSansPro;
import com.basu.R;
import com.basu.data.network.ApiEndPoint;
import com.basu.data.network.model.Country;
import com.basu.ui.personalemergencycontacts.EditContactDialog;
import com.basu.utils.VolleyClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryCode extends AppCompatActivity {

    private AutoCompleteEditTextSansPro auto_edt_search;

    private ArrayAdapter<String> adapter;

    private List<Country> countryList;
    private List<String> countryCode;
    private VolleyClass volleyClass;
    private String jwtToken;
    private String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_code);

        jwtToken = getIntent().getExtras().getString("jwToken");
        className = getIntent().getExtras().getString("class");

        volleyClass = new VolleyClass(this);

        auto_edt_search = findViewById(R.id.auto_edt_search);
        getCountryList();

        auto_edt_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String code = (String) parent.getItemAtPosition(position);
                Intent intent;
                if (className.equals(RegistrationActivity.class.getSimpleName()))
                    intent = new Intent(CountryCode.this, RegistrationActivity.class);
                else
                    intent = new Intent(CountryCode.this, EditContactDialog.class);

                intent.putExtra("code", code.substring(code.indexOf("[") + 1, code.indexOf("]")));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    private void getCountryList() {
        JSONObject urlObject = new JSONObject();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, ApiEndPoint.ENDPOINT_COUNTRY_CODE_RESPONSE, urlObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                countryList = new ArrayList<>();
                Log.e("Update Response: ", response.toString());
                try {
                    if (response.getBoolean("status")) {
                        JSONArray jsonArray = response.getJSONArray("response");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Country country = new Country();
                            country.setId(jsonObject.getInt("id"));
                            country.setName(jsonObject.getString("name"));
                            country.setPhonecode(jsonObject.getString("phonecode"));
                            countryList.add(country);
                        }
                        setCountryList(countryList);
                    } else {
                        Toast.makeText(CountryCode.this, response.getString("status_msg"), Toast.LENGTH_LONG).show();
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

    private void setCountryList(List<Country> countryList) {
        countryCode = new ArrayList<>();
        for (int i = 0; i < countryList.size(); i++) {
            Country country = countryList.get(i);
            String code = country.getName() + " [" + country.getPhonecode() + "] ";
            countryCode.add(code);
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, countryCode);
        auto_edt_search.setThreshold(0);
        auto_edt_search.setAdapter(adapter);
        auto_edt_search.showDropDown();
    }
}
