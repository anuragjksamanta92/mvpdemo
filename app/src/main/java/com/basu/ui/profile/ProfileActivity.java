package com.basu.ui.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.basu.R;
import com.basu.ui.base.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by root on 4/3/19.
 */

public class ProfileActivity extends BaseActivity implements ProfileMvpView {

    private static final int MY_PERMISSIONS_PHONE_STATE = 3;
    @BindView(R.id.image_view_profile)
    ImageView image_view_profile;
    @BindView(R.id.image_view_upload)
    ImageView image_view_upload;
    @BindView(R.id.ll_go_to_billing)
    LinearLayout ll_go_to_billing;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.edt_car_model)
    EditText edt_car_model;
    @BindView(R.id.edt_license_plate_number)
    EditText edt_license_plate_number;
    @BindView(R.id.edt_car_make)
    EditText edt_car_make;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;


    @Inject
    ProfileMvpPresenter<ProfileMvpView, ProfileMvpInteractor> mPresenter;
    private int MY_PERMISSIONS_CAMERA = 2;
    private String simSerial = "";

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        return intent;
    }

    private String imagePath;
    private int REQUEST_CAMERA = 0;
    private int SELECT_FILE = 1;
    private String encodedImage = "";
    private Uri uri;
    private String directory = "/data/data/com.basu/profileImage";
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_profile);
        changeToolBarColorActivity(ProfileActivity.this, R.color.profile_background_color);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(ProfileActivity.this);

        userName = mPresenter.getInteractor().getUser();
        if (userName != null && !userName.isEmpty())
            tv_user_name.setText(userName);

        /*TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            int simState = telephonyManager.getSimState();
            switch (simState) {
                case (TelephonyManager.SIM_STATE_ABSENT):
                    break;
                case (TelephonyManager.SIM_STATE_NETWORK_LOCKED):
                    break;
                case (TelephonyManager.SIM_STATE_PIN_REQUIRED):
                    break;
                case (TelephonyManager.SIM_STATE_PUK_REQUIRED):
                    break;
                case (TelephonyManager.SIM_STATE_UNKNOWN):
                    break;
                case (TelephonyManager.SIM_STATE_READY): {

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE},
                                MY_PERMISSIONS_PHONE_STATE);
                    } else {
                        simSerial = telephonyManager.getSimSerialNumber();
                        Log.e("ICCID: ", simSerial);
                    }
                }
            }
        }*/

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        image_view_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ProfileActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(ProfileActivity.this,
                                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                    selectImage();
                } else {
                    ActivityCompat.requestPermissions(ProfileActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA},
                            MY_PERMISSIONS_CAMERA);
                }
            }
        });
    }

    @OnClick(R.id.ll_go_to_billing)
    public void OnBillingButtonClick() {
        if (mPresenter.saveToDb(ProfileActivity.this, encodedImage, edt_car_make.getText().toString().trim(),
                edt_car_model.getText().toString().trim(), edt_license_plate_number.getText().toString().trim())) {
            mPresenter.completeRegistration(ProfileActivity.this, encodedImage, edt_car_make.getText().toString().trim(),
                    edt_car_model.getText().toString().trim(), edt_license_plate_number.getText().toString().trim(), simSerial);
        }
    }

    private void selectImage() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.camera_alerrt_title, null);
        final CharSequence[] items = {"Take Photo", "Select from gallery", "Cancel"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ProfileActivity.this);
        builder.setCustomTitle(view);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    cameraIntent();
                } else if (items[item].equals("Select from gallery")) {
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {

        File folder = new File(Environment.getExternalStorageDirectory(), directory);

        if (!folder.exists())
            folder.mkdirs();

        File file = new File(folder, System.currentTimeMillis() + ".jpg");
        try {
            if (file.createNewFile()) {
                Log.e("File", "Created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        imagePath = file.getAbsolutePath();

        if (Build.VERSION.SDK_INT >= N)
            uri = FileProvider.getUriForFile(ProfileActivity.this, getPackageName() + getString(R.string.app_file_provider_name), file);
        else uri = Uri.fromFile(file);

        if (uri != null) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            startActivityForResult(intent, REQUEST_CAMERA);
        }
    }


    private String onSelectFromGalleryResult(Intent data) {

        Bitmap bitmap = null;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        FileOutputStream fo;
        File file = null;
        if (data != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                bitmap.compress(Bitmap.CompressFormat.JPEG, 10, bytes);
                byte[] byteArrayImage = bytes.toByteArray();

                encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

                File folder = new File(Environment.getExternalStorageDirectory(), directory);

                if (!folder.exists())
                    folder.mkdirs();

                file = new File(folder, System.currentTimeMillis() + ".jpg");

                file.createNewFile();
                fo = new FileOutputStream(file);
                fo.write(bytes.toByteArray());
                fo.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        image_view_profile.setImageBitmap(bitmap);
        if (file != null) {
            return file.getAbsolutePath();
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                imagePath = onSelectFromGalleryResult(data);
                byte[] imageBytes = Base64.decode(encodeImageToBase64(imagePath), Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                image_view_profile.setImageBitmap(decodedImage);

            } else if (requestCode == REQUEST_CAMERA) {
                byte[] imageBytes = Base64.decode(encodeImageToBase64(imagePath), Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                image_view_profile.setImageBitmap(decodedImage);
            }
        }
    }

    private String encodeImageToBase64(String imagepath) {
        File actualImageFile;
        if (imagepath != null) {
            actualImageFile = new File(imagepath);
            try {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                Bitmap bitmap = new Compressor(this)
                        .setMaxWidth(320)
                        .setMaxHeight(320)
                        .setQuality(10)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .compressToBitmap(actualImageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 10, bytes);
                byte[] byteArrayImage = bytes.toByteArray();

                encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                Log.e("base64", encodedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encodedImage;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_CAMERA) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                selectImage();

            }
        }
    }
}