//package com.example.sueobmwodeudji.ui.sub_ui;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.DialogFragment;
//
//import com.example.sueobmwodeudji.databinding.FragmentTimeTableDialogBinding;
//import com.example.sueobmwodeudji.dto.TimeTableDTO;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.ArrayList;
//
//public class TimeTableFragmentDialog extends DialogFragment {
//    private FragmentTimeTableDialogBinding binding;
//    final String[] days = {"월요일", "화요일", "수요일", "목요일", "금요일"};
//    final String[] periods = {"1교시", "2교시", "3교시", "4교시", "5교시", "6교시", "7교시", "8교시"};
//    int daySelect = 0, periodSelect = 0;
//
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    CollectionReference ccc = db.collection("시간표");
//    DocumentReference ddd = ccc.document("T3");
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // 뒤로가기 버튼 & dialog 뒷배경 터치시 Dialog 종료
//        setCancelable(true);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//        binding = FragmentTimeTableDialogBinding.inflate(inflater, container, false);
//
//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, days);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.daySpin.setAdapter(adapter);
//
//        ArrayAdapter adapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, periods);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.periodSpin.setAdapter(adapter2);
//
//        // 고른 요일을 daySelect에 저장
//        binding.daySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.d ("고른거", days[position] + "숫자는 " + position);
//                daySelect = position;
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {}
//        });
//
//        // 고른 교시를 periodSelect에 저장
//        binding.periodSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.d ("고른거", periods[position] + "숫자는 " + position);
//                periodSelect = position;
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {}
//        });
//
//        // 추가 버튼을 누르면 firebase의 DB에 저장장
//       binding.btnSueobAdd.setOnClickListener(new View.OnClickListener() {
//            ArrayList<String> listt = new ArrayList<>();
//            @Override
//            public void onClick(View v) {
//                Log.d("테스트", "들어옴1");
//                String sueobName = binding.nameSueob.getText().toString();
//                ddd.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        TimeTableDTO dto = documentSnapshot.toObject(TimeTableDTO.class);
//                        String key = "";
//                        Log.d("테스트", "들어옴2");
//                        switch (daySelect) {
//                            case 0 : key = "mon"; listt = dto.getMon(); break;
//                            case 1 : key = "tue"; listt = dto.getTue(); break;
//                            case 2 : key = "wed"; listt = dto.getWed(); break;
//                            case 3 : key = "thu"; listt = dto.getThu(); break;
//                            case 4 : key = "fri"; listt = dto.getFri(); break;
//                        }
//                        listt.set(periodSelect, sueobName); Log.d("테스트", "들어옴4");
//                        ddd.update(key, listt);
//                    }
//                });
//            }
//        });
//
//
//
//        return binding.getRoot();
//    }
//
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//    }
//}
