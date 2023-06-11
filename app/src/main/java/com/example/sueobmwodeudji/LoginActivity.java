package com.example.sueobmwodeudji;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityLoginBinding;
import com.example.sueobmwodeudji.ui.coach_mark.Settings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String mId, mPw;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.loginBTN.setOnClickListener(v -> {
            login();
        });


        binding.registration.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user != null){
                successLogin();
            }
    }

    private void login() {
        mId = binding.loginID.getText().toString();
        mPw = binding.loginPW.getText().toString();

        if (isCompleted()) {
            binding.loginBTN.setClickable(false);
            mAuth.signInWithEmailAndPassword(mId, mPw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "signInWithEmail:success");
                                successLogin();
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                binding.loginBTN.setClickable(true);
                                Toast.makeText(LoginActivity.this, "로그인에 실패했습니다.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    private boolean isCompleted() {
        if (isIdEmpty(mId)) {
            Toast.makeText(this, "아이디 입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (isPwEmpty(mPw)) {
            Toast.makeText(this, "비밀번호입력이 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isIdEmpty(String id) {
        return id.equals("");
    }

    private boolean isPwEmpty(String pw) {
        return pw.equals("");
    }

    private void successLogin() {
        //Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();

        boolean first_registration = Settings.coachMarkLoad(this);
        Log.d("boolean", String.valueOf(first_registration));

        Intent intent;// 기존 Activity 제거
        if (first_registration) {
            intent = new Intent(this, CoachMarkActivity.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
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
