package cz.jiriskorpil.amixerwebui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import cz.jiriskorpil.amixerwebui.R;

/**
 * Main application activity.
 */
public class MainActivity extends AppCompatActivity
{
	private DataHandler dataHandler;
	private String lastUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

		dataHandler = (new DataHandler(this,
					(ListView) findViewById(R.id.controls_list),
					(SwipeRefreshLayout) findViewById(R.id.swipe_container)))
				.download();
		lastUrl = dataHandler.getBaseUrl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onResume()
	{
		super.onResume();

		if (!lastUrl.equals(dataHandler.getBaseUrl()))
		{
			dataHandler.download();
			lastUrl = dataHandler.getBaseUrl();
		}
	}

	public DataHandler getDataHandler()
	{
		return dataHandler;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();

		switch (id)
		{
			case R.id.action_settings:
				startActivity(new Intent(this, SettingsActivity.class));
				return true;
		}

		return super.onOptionsItemSelected(item);
	}
}