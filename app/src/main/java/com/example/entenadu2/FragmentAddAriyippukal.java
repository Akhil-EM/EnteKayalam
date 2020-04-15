package com.example.entenadu2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAddAriyippukal extends Fragment {
    View view;
      public EditText tittle,content;
      public Button add;
      Admin ad;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.freagment_add_ariyippukal,container,false);
        tittle=view.findViewById(R.id.add_tittle_ariyippu);
        content=view.findViewById(R.id.add_content_ariyippukal);
        add=view.findViewById(R.id.btn_add_content_comment);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad=(Admin) getActivity();
                ExtraFunction efs=new ExtraFunction();
                 Announce as=new Announce(tittle.getText().toString(),content.getText().toString(),efs.get_date_now());
                if((ad.insert_to_firebase(as)==true))
                {
                    tittle.setText(" ");
                    content.setText(" ");
                }

            }
        });
        return view;
    }
}
