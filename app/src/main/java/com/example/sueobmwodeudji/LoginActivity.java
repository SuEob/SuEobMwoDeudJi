package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String mId, mPw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginBTN.setOnClickListener(v -> {
            login();
        });

        binding.registration.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    private void login() {
        mId = binding.loginID.getText().toString();
        mPw = binding.loginPW.getText().toString();

        if(isCompleted()){
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("testUser")
                .document(mId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (Objects.equals(documentSnapshot.get("pw"), mPw)){
                            successLogin();
                            return;
                        }
                        failureLogin();
                    }
                });
        }
    }

    private boolean isCompleted() {
        if(isIdEmpty(mId)){
            Toast.makeText(this, "아이디 입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(isPwEmpty(mPw)){
            Toast.makeText(this, "비밀번호입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isIdEmpty(String id){
        return id.equals("");
    }
    private boolean isPwEmpty(String pw){
        return pw.equals("");
    }

    private void successLogin() {
        //Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // 기존 Activity 제거
        startActivity(intent);
        finish();
    }
    private void failureLogin() {
        Toast.makeText(this, "비밀번호입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 값을 정상적으로 받음()
            Toast.makeText(this, "정상", Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
