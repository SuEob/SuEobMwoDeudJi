package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityRegistrationBinding;
import com.example.sueobmwodeudji.dto.UserDTO;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationActivity extends AppCompatActivity {
    private ActivityRegistrationBinding binding;

    public static String school_name;
    private String mName, mSchool, mId, mPw;
    private boolean agree;

//    public static JSONObject schedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.registrationBTN.setOnClickListener(view -> {
//            if (schedule != null) {
//                //
//            }
//            school_name = "용문고등학교";
//             school_name = binding.registrationSchool.getText().toString();

            if(isCompleted()){
                createUser();
            }

        });

    }

    private boolean isCompleted(){
        mName = binding.registrationName.getText().toString();
        mSchool = binding.registrationSchool.getText().toString();
        mId = binding.registrationID.getText().toString();
        mPw = binding.registrationPW.getText().toString();

        if(isNameEmpty(mName)){
            Toast.makeText(this, "이름 입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(isSchoolEmpty(mSchool)){
            Toast.makeText(this, "학교 입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(isIdEmpty(mId)){
            Toast.makeText(this, "아이디 입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(isPwEmpty(mPw)){
            Toast.makeText(this, "비밀번호 입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!binding.registrationAgree.isChecked()){
            Toast.makeText(this, "개인정보취급방침에 동의해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isNameEmpty(String name){
        return name.equals("");
    }
    private boolean isSchoolEmpty(String school){
        return school.equals("");
    }
    private boolean isIdEmpty(String id){
        return id.equals("");
    }
    private boolean isPwEmpty(String pw){
        return pw.equals("");
    }

    private void createUser(){
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("testUser")
                .document(mId)
                .set(createUserDTO())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        success();
                    }
                });
    }

    private void success() {
        Intent intent = new Intent(this, LoginActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }

    private UserDTO createUserDTO(){
        UserDTO user = new UserDTO();
        user = new UserDTO();
        user.setName(mName);
        user.setId(mId);
        user.setPw(mPw);
        user.setSchool_name(mSchool);

        return user;
    }

}

