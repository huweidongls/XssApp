package com.jingna.xssapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.util.StringUtils;
import com.jingna.xssapp.util.ToastUtil;

/**
 * Created by Administrator on 2019/6/25.
 */

public class NumDialog extends Dialog {

    private Context context;
    private EditText etNum;
    private ImageView ivCancel;
    private ImageView ivSure;
    private ClickListener listener;

    public NumDialog(@NonNull Context context, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_num, null);
        setContentView(view);

        etNum = view.findViewById(R.id.et_num);
        ivCancel = view.findViewById(R.id.iv_cancel);
        ivSure = view.findViewById(R.id.iv_sure);

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ivSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = etNum.getText().toString();
                if(StringUtils.isEmpty(num)){
                    ToastUtil.showShort(context, "数量不能为空");
                }else {
                    listener.onClick(num);
                    dismiss();
                }
            }
        });

    }

    public interface ClickListener {
        void onClick(String num);
    }

}
