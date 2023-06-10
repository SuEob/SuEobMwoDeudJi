package com.example.sueobmwodeudji;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityRegistrationBinding;
import com.example.sueobmwodeudji.dto.UserDTO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistrationActivity extends AppCompatActivity {
    private ActivityRegistrationBinding binding;

    public static String school_name;
    private String mName, mSchool, mId, mPw;
    private boolean agree;
    private FirebaseAuth mAuth;
    private String mEmail;

//    public static JSONObject schedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        binding.registrationBTN.setOnClickListener(view -> {
//            if (schedule != null) {
//                //
//            }
//            school_name = "용문고등학교";
//             school_name = binding.registrationSchool.getText().toString();

            if (isCompleted()) {
                binding.registrationBTN.setClickable(false);
                createUser();
            }

        });

    }

    private boolean isCompleted() {
        mName = binding.registrationName.getText().toString();
        mSchool = binding.registrationSchool.getText().toString();
        mId = binding.registrationID.getText().toString();
        mPw = binding.registrationPW.getText().toString();

        if (isNameEmpty(mName)) {
            Toast.makeText(this, "이름 입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (isSchoolEmpty(mSchool)) {
            Toast.makeText(this, "학교 입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (isIdEmpty(mId)) {
            Toast.makeText(this, "아이디 입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (isPwEmpty(mPw)) {
            Toast.makeText(this, "비밀번호 입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!binding.registrationAgree.isChecked()) {
            Toast.makeText(this, "개인정보취급방침에 동의해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private boolean isNameEmpty(String name) {
        return name.equals("");
    }
    private boolean isSchoolEmpty(String school) {
        return school.equals("");
    }
    private boolean isIdEmpty(String id) {
        return id.equals("");
    }
    private boolean isPwEmpty(String pw) {
        return pw.equals("");
    }
    private void createUser() {
        mAuth.createUserWithEmailAndPassword(mId, mPw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(mName).build());
                            mEmail = user.getEmail();
                            success();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            binding.registrationBTN.setClickable(true);
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    private void success() {
        createUserDB();
        //Intent intent = new Intent(this, LoginActivity.class);
        //setResult(RESULT_OK, intent);
        finish();
    }

    private void createUserDB() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("사용자")
                .document(mEmail)
                .set(createUserDTO());
    }

    private UserDTO createUserDTO() {
        UserDTO user = new UserDTO();
//        user = new UserDTO();
//        user.setId(mId);
//        user.setPw(mPw);
        user.setName(mName);
        user.setSchool_name(mSchool);

        return user;
    }

}

