package net.cloud95.android.lession.location01;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Location01Activity extends Activity {

	private LocationManager locationManager;
	private TextView info;
	private Button click;

	private static final int LOCATION_SETTING = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		info = (TextView) findViewById(R.id.info);
		click = (Button) findViewById(R.id.click);

		click.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// 啟動系統的「位置資訊存取」設定,直接跳轉到設定畫面
				Intent intent = new Intent(
						android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivityForResult(intent, LOCATION_SETTING);
			}
		});

		setInfo();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 結束系統的「位置資訊存取」設定以後，重新顯示設定名稱
		if (requestCode == LOCATION_SETTING) {
			setInfo();
		}
	}

	// 讀取並顯示裝置所有已開啟的設備名稱
	private void setInfo() {
		// 取得已經開啟的設備名稱
		List<String> providers = locationManager.getProviders(true);// 如果是false則可以取得所有的定位方式(包含未開啟的功能)
		StringBuffer sb = new StringBuffer("Location Device Information:\n");

		for (String name : providers) {
			sb.append(name).append("\n");
		}

		info.setText(sb.toString());
	}

}
