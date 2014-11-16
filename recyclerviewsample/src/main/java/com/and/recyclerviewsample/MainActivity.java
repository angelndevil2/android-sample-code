package com.and.recyclerviewsample;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    NameRecyclerAdapter mAdapter;

    EditText mNameFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. get RecyclerView from layout
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_list);
        // 2. create LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // 3. plug in LayoutManager to RecyclerView
        recyclerView.setLayoutManager(layoutManager);
        // 3. create adapter
        mAdapter = new NameRecyclerAdapter();
        // 4. plug in Adapter to RecyclerView
        recyclerView.setAdapter(mAdapter);
        // 5. load test db data asynchronously
        new LoadTestDbTask().execute();

        mNameFilter = (EditText) findViewById(R.id.inputSearch);

        NameFilterWatcher nameFilterWatcher = new NameFilterWatcher();
        NameFilterWatcher.NameListChanger nameListChanger = new NameFilterWatcher.NameListChanger() {
            @Override
            public void changeNameList(String name) {
                new FilterNameTestDbTask().execute(name);
            }
        };
        nameFilterWatcher.setListChanger(nameListChanger);

        mNameFilter.addTextChangedListener(nameFilterWatcher);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class LoadTestDbTask extends AsyncTask<Void, Void, Cursor> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Cursor doInBackground(Void... params) {
            Cursor cursor = new TestDb(getApplicationContext()).getNames();
            mAdapter.setDataSet(cursor);
            return cursor;
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p/>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param cursor The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            mAdapter.notifyDataSetChanged();
        }
    }

    private class FilterNameTestDbTask extends AsyncTask<String, Void, Cursor> {
        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param names The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Cursor doInBackground(String... names) {
            Cursor cursor = new TestDb(getApplicationContext()).getNames(names[0]);
            mAdapter.setDataSet(cursor);
            return cursor;
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p/>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param cursor The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            mAdapter.notifyDataSetChanged();
        }
    }
}
