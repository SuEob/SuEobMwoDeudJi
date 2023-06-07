/*
package com.example.sueobmwodeudji.ui.dialog_ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.sueobmwodeudji.databinding.FragmentTimeTableDialogBinding;
import com.example.sueobmwodeudji.dto.TimeTableDTO;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TimeTableFragmentDialog extends DialogFragment {
    private FragmentTimeTableDialogBinding binding;

    final String[] days = {"월요일", "화요일", "수요일", "목요일", "금요일"};
    final String[] periods = {"1교시", "2교시", "3교시", "4교시", "5교시", "6교시", "7교시", "8교시"};
    int daySelect = 0, periodSelect = 0;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ccc = db.collection("시간표");
    DocumentReference ddd = ccc.document("T3");
    private int day_of_week_position;
    private int period_position;

    // 리스너를 만들기 위한 interface
    public interface TimeTableInterface {
        void onClick(String class_name, int day_of_week, int period);
    }
    private TimeTableInterface timeTableInterface;

    public void setTimeTableInterface(TimeTableInterface timeTableInterface) {
        this.timeTableInterface = timeTableInterface;
    }

    public static TimeTableFragmentDialog getInstance() {
        return new TimeTableFragmentDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true); // true -> 뒤로가기 버튼 or dialog 뒷배경 터치시 Dialog 종료
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentTimeTableDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String[] day_of_week = {"월요일", "화요일", "수요일", "목요일", "금요일"};
        final String[] period = {"1교시", "2교시", "3교시", "4교시", "5교시", "6교시", "7교시", "8교시", };

        // 요일 다이얼로그
        ArrayAdapter adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, day_of_week);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.daySpin.setAdapter(adapter1);

        binding.daySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day_of_week_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 교시 다이얼로그
        ArrayAdapter adapter2 = new ArrayAdapter(getContext().getApplicationContext(), android.R.layout.simple_spinner_item, period);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.periodSpin.setAdapter(adapter2);

        binding.daySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d ("고른거", days[position] + "숫자는 " + position);
                daySelect = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        binding.periodSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                period_position = position;
                Log.d ("고른거", periods[position] + "숫자는 " + position);
                periodSelect = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        // 부모 프래그먼트(TimeTableFragment)에 데이터 전송
        binding.btnSueobAdd.setOnClickListener(new View.OnClickListener() {

            ArrayList<String> listt = new ArrayList<>();
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "입력완료", Toast.LENGTH_SHORT).show();

                String value = binding.nameSueob.getText().toString();
                timeTableInterface.onClick(value, day_of_week_position, period_position);

                Log.d("테스트", "들어옴1");
                String sueobName = binding.nameSueob.getText().toString();
                ddd.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        TimeTableDTO dto = documentSnapshot.toObject(TimeTableDTO.class);
                        String key = "";
                        Log.d("테스트", "들어옴2");
                        switch (daySelect) {
                            case 0 : key = "mon"; listt = dto.getMon(); break;
                            case 1 : key = "tue"; listt = dto.getTue(); break;
                            case 2 : key = "wed"; listt = dto.getWed(); break;
                            case 3 : key = "thu"; listt = dto.getThu(); break;
                            case 4 : key = "fri"; listt = dto.getFri(); break;
                        }
                        listt.set(periodSelect, sueobName); Log.d("테스트", "들어옴4");
                        ddd.update(key, listt);
                    }
                });

                getDialog().dismiss();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
*/


package com.example.sueobmwodeudji.ui.dialog_ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.sueobmwodeudji.databinding.FragmentTimeTableDialogBinding;
import com.example.sueobmwodeudji.dto.TimeTableDTO;
import com.example.sueobmwodeudji.ui.TimeTableFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TimeTableFragmentDialog extends DialogFragment {
    private FragmentTimeTableDialogBinding binding;
    private int day_of_week_position;
    private int period_position;
    int ddd_year;
    int ddd_semester;

    public void setDdd_year(int ddd_year) {
        this.ddd_year = ddd_year;
    }
    public void setDdd_semester(int ddd_semester) {
        this.ddd_semester = ddd_semester;
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ccc = db.collection("시간표");

    // 리스너를 만들기 위한 interface
    public interface TimeTableInterface {
        void onClick(String class_name, int day_of_week, int period);
    }
    private TimeTableInterface timeTableInterface;

    public void setTimeTableInterface(TimeTableInterface timeTableInterface) {
        this.timeTableInterface = timeTableInterface;
    }

    public static TimeTableFragmentDialog getInstance() {
        return new TimeTableFragmentDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true); // true -> 뒤로가기 버튼 or dialog 뒷배경 터치시 Dialog 종료
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentTimeTableDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String[] day_of_week = {"월요일", "화요일", "수요일", "목요일", "금요일"};
        final String[] period = {"1교시", "2교시", "3교시", "4교시", "5교시", "6교시", "7교시", "8교시", };

        // 요일 다이얼로그
        ArrayAdapter adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, day_of_week);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.daySpin.setAdapter(adapter1);

        binding.daySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day_of_week_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 교시 다이얼로그
        ArrayAdapter adapter2 = new ArrayAdapter(getContext().getApplicationContext(), android.R.layout.simple_spinner_item, period);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.periodSpin.setAdapter(adapter2);

        binding.periodSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                period_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // 부모 프래그먼트(TimeTableFragment)에 데이터 전송
        binding.btnSueobAdd.setOnClickListener(new View.OnClickListener() {
            String ddd_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            DocumentReference ddd = ccc.document(ddd_email + " " + ddd_year + " - " + ddd_semester);

            ArrayList<String> listt = new ArrayList<>();
            @Override
            public void onClick(View v) {

                String sueobName = binding.nameSueob.getText().toString();
                ddd.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        TimeTableDTO dto = documentSnapshot.toObject(TimeTableDTO.class);

                        String key = "";
                        switch (day_of_week_position) {
                            case 0 : key = "mon"; listt = dto.getMon(); break;
                            case 1 : key = "tue"; listt = dto.getTue(); break;
                            case 2 : key = "wed"; listt = dto.getWed(); break;
                            case 3 : key = "thu"; listt = dto.getThu(); break;
                            case 4 : key = "fri"; listt = dto.getFri(); break;
                        }
                        listt.set(period_position, sueobName);
                        ddd.update(key, listt);
                    }
                });

                Toast.makeText(getContext(), "입력완료", Toast.LENGTH_SHORT).show();

                String value = binding.nameSueob.getText().toString();
                timeTableInterface.onClick(value, day_of_week_position, period_position);
                Log.d("ZZZ", String.valueOf(day_of_week_position));
                Log.d("ZZZ", String.valueOf(period_position));

                getDialog().dismiss();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
