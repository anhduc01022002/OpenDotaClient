package com.example.opendotaclient.ui;

import android.view.View;
import android.widget.AdapterView;

public interface SelectListener {
    void onItemClicked (AdapterView<?> parent, View view, int position, long id);

}
