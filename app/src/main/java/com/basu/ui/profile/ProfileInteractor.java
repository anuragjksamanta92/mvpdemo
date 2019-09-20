package com.basu.ui.profile;

import android.content.Context;
import android.util.Log;

import com.basu.data.db.model.User;
import com.basu.data.db.model.UserContacts;
import com.basu.data.db.repository.UserRepository;
import com.basu.data.network.ApiHelper;
import com.basu.data.network.model.finalRegistrationRequest.Billings;
import com.basu.data.network.model.finalRegistrationRequest.Car;
import com.basu.data.network.model.finalRegistrationRequest.Contacts;
import com.basu.data.network.model.finalRegistrationRequest.MemberId;
import com.basu.data.network.model.finalRegistrationRequest.RegistrationSuccessResponse;
import com.basu.data.network.model.finalRegistrationRequest.UserMember;
import com.basu.data.network.model.finalRegistrationRequest.UserResitrationRequest;
import com.basu.data.prefs.PreferencesHelper;
import com.basu.di.ApplicationContext;
import com.basu.ui.base.BaseInteractor;
import com.basu.utils.CommonUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by root on 4/3/19.
 */

public class ProfileInteractor extends BaseInteractor
        implements ProfileMvpInteractor {

    private UserRepository mUserRepository;

    @Inject
    public ProfileInteractor(@ApplicationContext Context context,
                             PreferencesHelper preferencesHelper,
                             ApiHelper apiHelper, UserRepository mUserRepository) {

        super(preferencesHelper, apiHelper);
        this.mUserRepository = mUserRepository;
    }


    @Override
    public int getCurrentUserLoggedInMode() {
        return getPreferencesHelper().getCurrentUserLoggedInMode();
    }

    @Override
    public boolean saveToDb(String encodedImage, String car_make, String car_model, String license_plate_number,
                            String currentUserId) {
        if (mUserRepository.checkUserExist(currentUserId).size() > 0) {
            User mUser = mUserRepository.checkUserExist(currentUserId).get(0);
            mUser.setMemberId(currentUserId);
            mUser.setCar_make(car_make);
            mUser.setCar_model(car_model);
            mUser.setLicense_plate_number(license_plate_number);
            mUser.setEncodedImage(encodedImage);
            mUserRepository.updateUser(mUser);
        } else {
            User mUser = new User();
            mUser.setMemberId(currentUserId);
            mUser.setCar_make(car_make);
            mUser.setCar_model(car_model);
            mUser.setLicense_plate_number(license_plate_number);
            mUser.setEncodedImage(encodedImage);
            mUserRepository.insertUser(mUser);
        }
        return true;
    }

    @Override
    public JSONObject completeRegistration(ProfileActivity profileActivity, String encodedImage, String car_make, String car_model, String license_plate_number, String simSerial) {

        List<User> mUser = mUserRepository.fetchMemberFromDb();

        MemberId memberId = new MemberId();
        memberId.setId(mUser.get(0).getMember_table_id());
        memberId.setMember_id(mUser.get(0).getMemberId());

        UserMember userMember = new UserMember();
        userMember.setEmail(mUser.get(0).getEmail());
        userMember.setCountry_code(mUser.get(0).getCountry_code());
        userMember.setPhone(mUser.get(0).getPhone_no());
        userMember.setName(mUser.get(0).getName());
        userMember.setPhoto(encodedImage);
        userMember.setAddress(mUser.get(0).getAddress_1());
        userMember.setAddress_line_1(mUser.get(0).getAddress_2());
        userMember.setCity(mUser.get(0).getCity());
        userMember.setState(mUser.get(0).getState());
        userMember.setZip(mUser.get(0).getZip());
        userMember.setCountry(mUser.get(0).getCountry());
        userMember.setDevice_id(CommonUtils.getDeviceId(profileActivity));
//        userMember.setDevice_id("G2AGA1V5AV5AVA5");
        userMember.setDevice_type("ANDROID");
        userMember.setSim_id(simSerial);

        String pin = mUser.get(0).getPin();

        List<UserContacts> userContactsList = mUserRepository.getAllContacts(mUser.get(0).getMemberId());
        List<Contacts> contactsList = new ArrayList<>();
        for (int i = 0; i < userContactsList.size(); i++) {
            UserContacts userContacts = userContactsList.get(i);
            Contacts contacts = new Contacts();
            contacts.setContact_name(userContacts.getContactName());
            contacts.setContact_country_code(userContacts.getContactPin());
            contacts.setContact_phone(userContacts.getContactNo());
            contactsList.add(i, contacts);
        }

        Car car = new Car();
        car.setMake(car_make);
        car.setLicence_number(license_plate_number);
        car.setModel(car_model);

        Billings billings = new Billings();

        UserResitrationRequest userResitrationRequest = new UserResitrationRequest();
        userResitrationRequest.setMemberId(memberId);
        userResitrationRequest.setUserMember(userMember);
        userResitrationRequest.setContactsList(contactsList);
        userResitrationRequest.setPin(pin);
        userResitrationRequest.setCar(car);
        userResitrationRequest.setBillings(billings);

        Gson gson = new Gson();

        String requestJson = gson.toJson(userResitrationRequest);

        JSONObject request = new JSONObject();
        try {
            request = new JSONObject(requestJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("JSON", requestJson);

        return request;


//        return userResitrationRequest;


    }

    @Override
    public Observable<RegistrationSuccessResponse> doServerCompleteRegistrationApiCall(JSONObject requestJson) {
        return getApiHelper().doServerCompleteRegistrationApiCall(getJwtToken(), requestJson);
    }

    @Override
    public String getUser() {
        return mUserRepository.fetchMemberFromDb().get(0).getName();
    }

    private String getJwtToken() {
        return getPreferencesHelper().getjwttoken();
    }


}
